package by.epam.hubarevich.dao.impl;

import by.epam.hubarevich.dao.exception.DAOException;
import by.epam.hubarevich.dao.AuthorDAO;
import by.epam.hubarevich.dao.util.ProxyConnection;
import by.epam.hubarevich.dao.util.QueryList;
import by.epam.hubarevich.domain.Author;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of AuthorDAO. Contains methods realisations
 * @author Anton_Hubarevich
 * @version 1.0
 */
@Component
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    private BasicDataSource dataSource;

    /**
     * @return List of all Authors from database
     * @throws DAOException if SQLException thrown
     */
    @Override
    public Set<Author> findAll() throws DAOException {
        Author author;
        Set<Author> authors = new HashSet<Author>();
        try (Connection connection = ProxyConnection.getConnection(dataSource);
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.FIND_ALL_AUTHORS.getValue())) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    author = new Author();
                    configAuthor(resultSet, author);
                    authors.add(author);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return authors;
    }


    @Override
    public Author findDomainById(int id) throws DAOException {
        Author author = null;
        try (Connection connection = ProxyConnection.getConnection(dataSource);
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.FIND_AUTHOR_BY_ID.getValue())) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                author = new Author();
                configAuthor(resultSet, author);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return author;
    }

    @Override
    public void delete(int id) throws DAOException {

        try (Connection connection = ProxyConnection.getConnection(dataSource);
             PreparedStatement preparedStatement =
                     connection.prepareStatement(QueryList.DELETE_AUTHOR.getValue())) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public int create(Author author) throws DAOException {
        int authorId = 0;
        try (Connection connection = ProxyConnection.getConnection(dataSource);
             PreparedStatement preparedStatement =
                     connection.prepareStatement(QueryList.CREATE_NEW_AUTHOR.getValue(),
                             new String[]{AUTHOR_COLUMNS.AUTHOR_ID.name()})) {
            preparedStatement.setString(1, author.getAuthorName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                authorId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException();
        }
        return authorId;
    }

    @Override
    public void update(Author author) throws DAOException {
        try (Connection connection = ProxyConnection.getConnection(dataSource);
             PreparedStatement preparedStatement =
                     connection.prepareStatement(QueryList.UPDATE_AUTHOR.getValue())) {
            preparedStatement.setString(1, author.getAuthorName());
            preparedStatement.setTimestamp(2, author.getExpired());
            preparedStatement.setInt(3, author.getAuthorId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Author findAuthorByName(String authorName) throws DAOException {
        Author author = null;
        try (Connection connection = ProxyConnection.getConnection(dataSource);
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.FIND_AUTHOR_BY_NAME.getValue())) {
            preparedStatement.setString(1, authorName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                author = new Author();
                configAuthor(resultSet, author);
            }
        } catch (SQLException e) {
            throw new DAOException(e);

        }
        return author;
    }

    @Override
    public Author findAuthorByNewsId(int newsId) throws DAOException {
        Author author = null;
        try (Connection connection = ProxyConnection.getConnection(dataSource);
             PreparedStatement preparedStatement =
                     connection.prepareStatement(QueryList.FIND_AUTHORS_BY_NEWS_ID.getValue())) {
            preparedStatement.setInt(1, newsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    author = new Author();
                    configAuthor(resultSet, author);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return author;
    }


    private void configAuthor(ResultSet resultSet, Author author) throws SQLException {
        author.setAuthorId(resultSet.getInt(AUTHOR_COLUMNS.AUTHOR_ID.name()));
        author.setAuthorName(resultSet.getString(AUTHOR_COLUMNS.AUTHOR_NAME.name()));
        author.setExpired(resultSet.getTimestamp(AUTHOR_COLUMNS.EXPIRED.name()));
    }

    public enum AUTHOR_COLUMNS {
        AUTHOR_ID, AUTHOR_NAME, EXPIRED
    }
}
