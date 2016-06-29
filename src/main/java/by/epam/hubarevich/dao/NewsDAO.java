package by.epam.hubarevich.dao;

import by.epam.hubarevich.dao.exception.DAOException;
import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.domain.News;
import by.epam.hubarevich.domain.Tag;

import java.util.Set;

/**
 * Interface for News message database operations
 * @author Anton_Hubarevich
 * @version 1.0
 */

public interface NewsDAO extends AbstractDAO<News>{
    /**
     * Adds Author object to News message object
     * @param newsId Integer value of News message identifier
     * @param authorId Integer value of Author identifier
     * @throws DAOException if SQL exception
     */
    void addNewsAuthor(int newsId,int authorId) throws DAOException;

    /**
     * Gets the Set of most commented news
     * @param newsQuantity Integer size of result Set
     * @return Set of News objects
     * @throws DAOException in case of SQL exception
     */
    Set<News> getMostCommentedNews (int newsQuantity) throws DAOException;

    /**
     * Adds the Set of Tag objects to News message
     * @param newsId Integer value of News message identifier
     * @param tags Set of Tag objects
     * @throws DAOException in case of SQL exception
     */
    void addTagsNews (int newsId,Set<Tag> tags) throws DAOException;

    /**
     * Searches for a News messages according to Author ID
     * @param author Author object
     * @return Set of News messages
     * @throws DAOException in case of SQL exception
     */
    Set<News> findNewsByAuthor(Author author) throws DAOException;

    /**
     * Searches for a News messages according to Author ID
     * @param tags Set of Tag objects to search
     * @return Set of News messages
     * @throws DAOException in case of SQL exception
     */

    Set<News> findNewsByTags(Set<Tag>tags) throws DAOException;



}
