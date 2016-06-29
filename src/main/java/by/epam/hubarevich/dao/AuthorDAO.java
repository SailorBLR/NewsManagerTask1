package by.epam.hubarevich.dao;

import by.epam.hubarevich.dao.exception.DAOException;
import by.epam.hubarevich.domain.Author;

/**
 * Interface for Author database operations
 * @author Anton_Hubarevich
 * @version 1.0
 */
public interface AuthorDAO extends AbstractDAO <Author>{

    /**
     * Searches for database field with the defined AUTHOR_NAME
     * @param authorName String value of AUTHOR_NAME
     * @return Author object
     * @throws DAOException in case of SQL exception
     */
    Author findAuthorByName (String authorName) throws DAOException;

    /**
     * Searches for database field based on NEWS_ID
     * @param newsId Integer value of NEWS_ID unique identifier
     * @return Author object
     * @throws DAOException in case of SQL exception
     */
    Author findAuthorByNewsId (int newsId) throws DAOException;
}
