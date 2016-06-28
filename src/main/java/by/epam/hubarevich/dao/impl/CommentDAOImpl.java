package by.epam.hubarevich.dao.impl;

import by.epam.hubarevich.dao.QueryList;
import by.epam.hubarevich.dao.exceptions.DAOException;
import by.epam.hubarevich.dao.AbstractDAO;
import by.epam.hubarevich.dao.CommentDAO;
import by.epam.hubarevich.domain.Comment;
import com.sun.org.apache.bcel.internal.generic.DDIV;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import sun.text.resources.no.CollationData_no;

import javax.naming.ldap.PagedResultsControl;
import java.sql.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
@Component
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    BasicDataSource dataSource;

    @Override
    public Set<Comment> findAll() throws DAOException {
        Comment comment = new Comment();
        Set<Comment> comments = new HashSet<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.FIND_ALL_COMMENTS.getValue())) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    configComment(resultSet, comment);
                    comments.add(comment);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return comments;
    }

    @Override
    public Comment findDomainById(int id) throws DAOException {
        Comment comment = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.FIND_COMMENT_BY_ID.getValue())) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                comment = new Comment();
                configComment(resultSet, comment);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return comment;
    }

    @Override
    public void delete(int id) throws DAOException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.DELETE_COMMENT.getValue())) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public int create(Comment comment) throws DAOException {
        int commentId = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.ADD_COMMENT_TO_NEWS.getValue(),new String[]{COMMENTS_COLUMNS.COMMENT_ID.name()})) {

            preparedStatement.setInt(1,comment.getNewsId());
            preparedStatement.setString(2,comment.getCommentText());
            if(comment.getCommentCreationDate()==null){
                preparedStatement.setTimestamp(3,timestamp);
            } else {
                preparedStatement.setTimestamp(3,comment.getCommentCreationDate());
            }
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                commentId=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return commentId;
    }

    @Override
    public void update(Comment comment) throws DAOException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.UPDATE_COMMENT.getValue())) {

            preparedStatement.setString(1, comment.getCommentText());
            preparedStatement.setInt(2,comment.getCommentId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Set<Comment> findCommentsByNewsId(int newsId) throws DAOException{
        Comment comment = new Comment();
        Set<Comment> comments = new HashSet<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.FIND_COMMENT_BY_NEWS_ID.getValue())) {
            preparedStatement.setInt(1,newsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    configComment(resultSet, comment);
                    comments.add(comment);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return comments;
    }

    @Override
    public Set<Comment> findCommentsByUserId(int authorId) {
        return null;
    }

    private void configComment(ResultSet resultSet, Comment comment) throws SQLException {
        comment.setCommentId(resultSet.getInt(COMMENTS_COLUMNS.COMMENT_ID.name()));
        comment.setNewsId(resultSet.getInt(COMMENTS_COLUMNS.NEWS_ID.name()));
        comment.setCommentText(resultSet.getString(COMMENTS_COLUMNS.COMMENT_TEXT.name()));
        comment.setCommentCreationDate(resultSet.getTimestamp(COMMENTS_COLUMNS.CREATION_DATE.name()));
    }

    public enum COMMENTS_COLUMNS {
        COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE
    }
}
