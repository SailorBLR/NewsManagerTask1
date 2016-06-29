package by.epam.hubarevich.dao;

import by.epam.hubarevich.dao.exception.DAOException;
import by.epam.hubarevich.domain.Comment;

import java.util.Set;

/**
 * Interface for Comment database operations
 * @author Anton_Hubarevich
 * @version 1.0
 */

public interface CommentDAO extends AbstractDAO<Comment> {

    /**
     * Searches for a database fields based on NEWS_ID
     * @param newsId Integer value of unique News identifier
     * @return Set of comments for defined News message
     * @throws DAOException in case of SQL exception
     */
    Set<Comment> findCommentsByNewsId (int newsId) throws DAOException;

    Set<Comment> findCommentsByUserId (int authorId) throws DAOException;
}
