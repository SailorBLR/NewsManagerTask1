package by.epam.hubarevich.service.impl;

import by.epam.hubarevich.dao.CommentDAO;
import by.epam.hubarevich.dao.NewsDAO;
import by.epam.hubarevich.dao.exception.DAOException;
import by.epam.hubarevich.dao.impl.CommentDAOImpl;
import by.epam.hubarevich.dao.impl.NewsDAOImpl;
import by.epam.hubarevich.domain.Comment;
import by.epam.hubarevich.service.CommentService;
import by.epam.hubarevich.service.exception.LogicException;
import by.epam.hubarevich.utils.CommentCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Anton_Hubarevich on 6/27/2016.
 */
public class CommentServiceImpl implements CommentService {

    private static CommentServiceImpl instance = new CommentServiceImpl();

    public static CommentServiceImpl getInstance () {
        return instance;
    }

    private CommentServiceImpl () {}

    @Autowired
    CommentDAO commentDAO = new CommentDAOImpl();

    @Autowired
    NewsDAO newsDAO = new NewsDAOImpl();

    @Override
    public int addComment(Comment comment) throws LogicException{
        int commentId = 0;
        if (CommentCheckUtil.checkComment(comment)) {
            try {
                commentId=commentDAO.create(comment);
            } catch (DAOException e) {
                throw new LogicException(e);
            }
        }
        return commentId;
    }

    @Override
    public void deleteComment(int commentId)  throws LogicException{
        if (commentId>0) {
            try {
                commentDAO.delete(commentId);
            } catch (DAOException e) {
                throw new LogicException(e);
            }
        }
    }

    @Override
    public void updateComment(Comment comment)  throws LogicException{
        if (CommentCheckUtil.checkComment(comment)) {
            try {
                commentDAO.update(comment);
            } catch (DAOException e) {
                throw new LogicException(e);
            }
        }
    }
}
