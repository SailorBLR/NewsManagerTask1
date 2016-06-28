package by.epam.hubarevich.dao.impl;

import by.epam.hubarevich.dao.NewsDAO;
import by.epam.hubarevich.dao.QueryList;
import by.epam.hubarevich.dao.exceptions.DAOException;
import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.domain.News;
import by.epam.hubarevich.domain.Tag;
import by.epam.hubarevich.utils.TimestamperUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
@Component
public class NewsDAOImpl implements NewsDAO {

    @Autowired
    BasicDataSource dataSource;

    @Override
    public Set<News> findAll() throws DAOException {
        Set<News> newses = new HashSet<News>();
        News news;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.FIND_ALL_NEWS.getValue())) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                news = new News();
                configNews(resultSet, news);
                newses.add(news);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return newses;
    }

    @Override
    public News findDomainById(int id) throws DAOException {

        News news = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(QueryList.FIND_NEWS_BY_ID.getValue())) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                news = new News();
                configNews(resultSet, news);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return news;
    }

    @Override
    public void delete(int id) throws DAOException {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.DELETE_NEWS.getValue())) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }


    @Override
    public int create(News news) throws DAOException {

        int newsId = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.CREATE_NEWS.getValue(),
                     new String[]{NEWS_COLUMNS.NEWS_ID.name()})) {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getShortText());
            preparedStatement.setString(3, news.getFullText());
            preparedStatement.setTimestamp(4, TimestamperUtil.makeTimeStamp());
            preparedStatement.setTimestamp(5, TimestamperUtil.makeTimeStamp());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                newsId = resultSet.getInt(1);
                news.setNewsId(newsId);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return newsId;
    }

    @Override
    public void update(News news) throws DAOException {

        Date date = new Date(Calendar.getInstance().getTime().getTime());
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.UPDATE_NEWS.getValue())) {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getShortText());
            preparedStatement.setString(3, news.getFullText());
            preparedStatement.setDate(4, date);
            preparedStatement.setInt(5, news.getNewsId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void addNewsAuthor(int newsId, int authorId) throws DAOException {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.ADD_AUTHOR_TO_NEWS.getValue())) {
            preparedStatement.setInt(1, newsId);
            preparedStatement.setInt(2, authorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public Set<News> getMostCommentedNews(int newsQuantity) throws DAOException {

        Set<News> newses = new HashSet<>();
        News news = new News();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.GET_MOST_COMMENTED_NEWS.getValue())) {
            preparedStatement.setInt(1, newsQuantity);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                configNews(resultSet, news);
                newses.add(news);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return newses;
    }

    @Override
    public void addTagsNews(int newsId, Set<Tag> tags) throws DAOException {

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement(QueryList.ADD_TAG_TO_NEWS.getValue())) {
            for (Tag tag : tags) {
                preparedStatement.setInt(1, newsId);
                preparedStatement.setInt(2, tag.getTagId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Set<News> findNewsByAuthor(Author author) throws DAOException {
        Set<News> newses = new HashSet<>();
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement(QueryList.FIND_NEWS_BY_AUTHOR.getValue())) {
            preparedStatement.setInt(1, author.getAuthorId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                News news = new News();
                configNews(resultSet,news);
                newses.add(news);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return newses;
    }

    @Override
    public Set<News> findNewsByTags(Set<Tag> tags) throws DAOException {
        Set<News> newses = new HashSet<>();
        String query = QueryList.FIND_NEWS_BY_TAGS.getValue();
        for (int i=0;i<tags.size()-1;i++) {
            query = query.concat(QueryList.ANOTHER_TAG.getValue());
        }
        try (Connection connection=dataSource.getConnection(); PreparedStatement preparedStatement
                =connection.prepareStatement(QueryList.FIND_NEWS_BY_TAGS.getValue())) {
            for (int i=1;i<tags.size();i++){
                preparedStatement.setInt(i,tags.iterator().next().getTagId());
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                News news = new News();
                configNews(resultSet,news);
                newses.add(news);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return newses;
    }

    private void configNews(ResultSet resultSet, News news) throws SQLException {
        news.setNewsId(resultSet.getInt(NEWS_COLUMNS.NEWS_ID.name()));
        news.setTitle(resultSet.getString(NEWS_COLUMNS.TITLE.name()));
        news.setShortText(resultSet.getString(NEWS_COLUMNS.SHORT_TEXT.name()));
        news.setFullText(resultSet.getString(NEWS_COLUMNS.FULL_TEXT.name()));
        news.setNewsCreationDate(resultSet.getTimestamp(NEWS_COLUMNS.CREATION_DATE.name()));
        news.setNewsModificationDate(resultSet.getDate(NEWS_COLUMNS.MODIFICATION_DATE.name()));
    }

    public enum NEWS_COLUMNS {
        NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE
    }
}
