package by.epam.hubarevich.dao;

import by.epam.hubarevich.dao.exceptions.DAOException;
import by.epam.hubarevich.domain.Comment;

import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
public interface CommentDAO extends AbstractDAO<Comment> {
    Set<Comment> findCommentsByNewsId (int newsId) throws DAOException;
    Set<Comment> findCommentsByUserId (int authorId) throws DAOException;
}
