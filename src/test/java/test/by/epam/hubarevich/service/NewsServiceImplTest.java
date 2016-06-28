package test.by.epam.hubarevich.service;

import by.epam.hubarevich.dao.AuthorDAO;
import by.epam.hubarevich.dao.NewsDAO;
import by.epam.hubarevich.dao.TagDAO;
import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.domain.News;
import by.epam.hubarevich.domain.Tag;
import by.epam.hubarevich.domain.dto.NewsDTO;
import by.epam.hubarevich.domain.dto.SearchDTO;
import by.epam.hubarevich.service.exception.LogicException;
import by.epam.hubarevich.service.impl.NewsServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Anton_Hubarevich on 6/24/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

    @InjectMocks
    private NewsServiceImpl newsService;

    @Mock
    private NewsDAO newsDAO;
    @Mock
    private AuthorDAO authorDAO;
    @Mock
    private TagDAO tagDAO;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateNews() throws Exception {
        News news = new News(1,"f","f","gg",Timestamp.valueOf("1988-12-11 08:45:33"),
                java.sql.Date.valueOf("1989-12-11"));
        NewsDTO newsDTO = new NewsDTO();
        Tag tag = new Tag(1,"Belarus");
        Set<Tag> tags = new HashSet<>();
        Author author = new Author(1,"Name");
        tags.add(tag);
        newsDTO.setNews(news);
        newsDTO.setTags(tags);
        newsDTO.setAuthor(author);
        when(newsDAO.create(newsDTO.getNews())).thenReturn(1);
        Assert.assertEquals(1, newsService.createNews(newsDTO));
    }

    @Test(expected = NumberFormatException.class)
    public void testDeleteNews() throws Exception {
        doThrow(new NumberFormatException()).when(newsDAO).delete(anyInt());
        newsService.deleteNews(anyInt());
    }

    @Test (expected = NumberFormatException.class)
    public void testUpdate() throws Exception {
        News news = new News(1,"f","f","gg",Timestamp.valueOf("1988-12-11 08:45:33"),
                java.sql.Date.valueOf("1989-12-11"));
        NewsDTO newsDTO = new NewsDTO();
        Tag tag = new Tag(1,"Belarus");
        Set<Tag> tags = new HashSet<>();
        Author author = new Author(1,"Name");
        tags.add(tag);
        newsDTO.setNews(news);
        newsDTO.setTags(tags);
        newsDTO.setAuthor(author);
        doThrow(new NumberFormatException()).when(newsDAO).update(news);
        newsService.updateNews(newsDTO);
    }

    @Test
    public void testSearchForNewsAuthor() throws Exception {
        Author author = new Author();
        Tag tag = new Tag(1,"Belarus");
        SearchDTO searchDTO = new SearchDTO();
        Set<News> newses = new HashSet<>();
        Set<Tag> tags = new HashSet<>();
        News news = new News(1,"f","f","gg",Timestamp.valueOf("1988-12-11 08:45:33"),
                java.sql.Date.valueOf("1988-12-11"));
        tags.add(tag);
        author.setAuthorId(1);
        newses.add(news);
        searchDTO.setAuthor(author);
        when(newsDAO.findNewsByAuthor(author)).thenReturn(newses);
        when(authorDAO.findAuthorByNewsId(anyInt())).thenReturn(author);
        when(tagDAO.getTagsByNewsId(anyInt())).thenReturn(tags);
        Assert.assertEquals(newses.toString().substring(1,newses.toString().length()-1),
                newsService.searchForNews(searchDTO).iterator().next().getNews().toString());
    }

    @Test
    public void testSearchForNewsTags() throws Exception {
        Author author = new Author();
        Tag tag = new Tag(1,"Belarus");
        SearchDTO searchDTO = new SearchDTO();
        Set<News> newses = new HashSet<>();
        Set<Tag> tags = new HashSet<>();
        News news = new News(1,"f","f","gg",Timestamp.valueOf("1988-12-11 08:45:33"),
                java.sql.Date.valueOf("1988-12-11"));
        tags.add(tag);
        author.setAuthorId(1);
        newses.add(news);
        searchDTO.setTags(tags);
        when(newsDAO.findNewsByTags(tags)).thenReturn(newses);
        when(authorDAO.findAuthorByNewsId(anyInt())).thenReturn(author);
        when(tagDAO.getTagsByNewsId(anyInt())).thenReturn(tags);
        Assert.assertEquals(newses.toString().substring(1,newses.toString().length()-1),
                newsService.searchForNews(searchDTO).iterator().next().getNews().toString());
    }


    @Test
    public void testSearchForNewsCommented() throws Exception {

        Set<News> newses = new HashSet<>();
        when(newsDAO.getMostCommentedNews(2)).thenReturn(newses);
        when(newsDAO.findAll()).thenReturn(newses);
        assertThat(newsService.searchForNewsCommented(true,2).contains(newses));

    }


}
