package by.epam.hubarevich.service.impl;

import by.epam.hubarevich.dao.AuthorDAO;
import by.epam.hubarevich.dao.NewsDAO;
import by.epam.hubarevich.dao.TagDAO;
import by.epam.hubarevich.dao.exceptions.DAOException;
import by.epam.hubarevich.dao.impl.AuthorDAOImpl;
import by.epam.hubarevich.dao.impl.NewsDAOImpl;
import by.epam.hubarevich.dao.impl.TagDAOImpl;
import by.epam.hubarevich.domain.News;
import by.epam.hubarevich.domain.dto.NewsDTO;
import by.epam.hubarevich.domain.dto.SearchDTO;
import by.epam.hubarevich.service.NewsService;
import by.epam.hubarevich.service.exception.LogicException;
import by.epam.hubarevich.utils.NewsCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/23/2016.
 */
@Component
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsDAO newsDAO = new NewsDAOImpl();

    @Autowired
    AuthorDAO authorDAO = new AuthorDAOImpl();

    @Autowired
    TagDAO tagDao = new TagDAOImpl();

    private static NewsServiceImpl instance = new NewsServiceImpl();

    public static NewsServiceImpl getInstance() {
        return instance;
    }

    private NewsServiceImpl() {
    }

    @Override
    public int createNews(NewsDTO newsDTO) throws LogicException {
        int successMarker = 0;

        if (NewsCheckUtil.checkNewsDto(newsDTO)) {
            try {
                successMarker = newsDAO.create(newsDTO.getNews());
                if (successMarker != 0) {
                    newsDAO.addTagsNews(newsDTO.getNews().getNewsId(), newsDTO.getTags());
                    newsDAO.addNewsAuthor(newsDTO.getNews().getNewsId(), newsDTO.getAuthor().getAuthorId());
                }
            } catch (DAOException e) {
                throw new LogicException(e);
            }
        }
        return successMarker;
    }

    @Override
    public void deleteNews(int newsId) throws LogicException {
        try {
            newsDAO.delete(newsId);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public Set<NewsDTO> searchForNews(SearchDTO searchDTO) throws LogicException {
        Set<News> newses = null;
        if (NewsCheckUtil.checkSearchDto(searchDTO)) {
            try {
                if (searchDTO.getAuthor() != null) {
                    newses = newsDAO.findNewsByAuthor(searchDTO.getAuthor());
                } else {
                    newses = newsDAO.findNewsByTags(searchDTO.getTags());
                }
            } catch (DAOException e) {
                throw new LogicException(e);
            }
        }
        return makeDto(newses);
    }

    @Override
    public int updateNews(NewsDTO newsDTO) throws LogicException {
        int successMarker = 0;
        if (NewsCheckUtil.checkNewsDto(newsDTO)) {
            try {
                newsDAO.update(newsDTO.getNews());
                successMarker = 1;
            } catch (DAOException e) {
                throw new LogicException();
            }
        }

        return successMarker;
    }

    @Override
    public Set<NewsDTO> searchForNewsCommented(boolean mostCommented,
                                               int quantityMostCommented) throws LogicException {
        Set<News> newses;
        try {
            if (!mostCommented) {
                newses=newsDAO.findAll();
            } else {
                newses=newsDAO.getMostCommentedNews(2);
            }

        } catch (DAOException e) {
            throw new LogicException(e);
        }
        return makeDto(newses);
    }

    private Set<NewsDTO> makeDto (Set<News> newses) throws LogicException {
        Set<NewsDTO> newsDTOs = new HashSet<>();
        try {
            for (News news : newses) {
                NewsDTO newsDTO = new NewsDTO();
                newsDTO.setNews(news);
                newsDTO.setAuthor(authorDAO.findAuthorByNewsId(news.getNewsId()));
                newsDTO.setTags(tagDao.getTagsByNewsId(news.getNewsId()));
                newsDTOs.add(newsDTO);
            }
        }catch (DAOException e) {
            throw new LogicException (e);
        }
        return newsDTOs;
    }
}
