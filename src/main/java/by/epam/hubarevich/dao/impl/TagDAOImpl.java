package by.epam.hubarevich.dao.impl;

import by.epam.hubarevich.dao.util.ProxyConnection;
import by.epam.hubarevich.dao.util.QueryList;
import by.epam.hubarevich.dao.TagDAO;
import by.epam.hubarevich.dao.exception.DAOException;
import by.epam.hubarevich.domain.Tag;
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
 * Implementation of TagDAO. Contains methods realisations
 * @author Anton_Hubarevich
 * @version 1.0
 */
@Component
public class TagDAOImpl implements TagDAO {

    @Autowired
    private BasicDataSource dataSource;

    @Override
    public Set<Tag> findAll() throws DAOException {
        return null;
    }

    @Override
    public Tag findDomainById(int id) throws DAOException {
        return null;
    }

    @Override
    public void delete(int id) throws DAOException {
    }

    @Override
    public int create(Tag domain) throws DAOException {
        return 0;
    }

    @Override
    public void update(Tag domain) throws DAOException {
    }

    @Override
    public Set<Tag> getTagsByNewsId(int newsId) throws DAOException {

        Set<Tag> tags = new HashSet<Tag>();
        Tag tag = new Tag();
        try (Connection connection = ProxyConnection.getConnection(dataSource);
             PreparedStatement preparedStatement
                     = connection.prepareStatement(QueryList.FIND_TAGS_BY_NEWS_ID.getValue())) {
            preparedStatement.setInt(1,newsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    configTag(resultSet,tag);
                    tags.add(tag);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return tags;
    }

    private void configTag(ResultSet resultSet, Tag tag) throws SQLException {
        tag.setTagId(resultSet.getInt(TAG_COLUMNS.TAG_ID.name()));
        tag.setTagName(resultSet.getString(TAG_COLUMNS.TAG_NAME.name()));
    }
    public enum TAG_COLUMNS {
        TAG_ID, TAG_NAME
    }
}
