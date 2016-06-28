package by.epam.hubarevich.dao;

import by.epam.hubarevich.dao.exceptions.DAOException;
import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.domain.News;
import by.epam.hubarevich.domain.Tag;

import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
public interface NewsDAO extends AbstractDAO<News>{
    void addNewsAuthor(int newsId,int authorId) throws DAOException;
    Set<News> getMostCommentedNews (int newsQuantity) throws DAOException;
    void addTagsNews (int newsId,Set<Tag> tags) throws DAOException;
    Set<News> findNewsByAuthor(Author author) throws DAOException;
    Set<News> findNewsByTags(Set<Tag>tags) throws DAOException;

}
