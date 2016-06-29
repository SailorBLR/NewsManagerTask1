package by.epam.hubarevich.dao;

import by.epam.hubarevich.dao.exception.DAOException;
import by.epam.hubarevich.domain.Tag;

import java.util.Set;

/**
 * Interface for Tag operations
 * @author Anton_Hubarevich
 * @version 1.0
 */
public interface TagDAO extends AbstractDAO<Tag> {
    /**
     * Searches for Tags according to News identifier
     * @param newsId Integer value
     * @return Set of Tags
     * @throws DAOException in case of SQL exception
     */
    Set<Tag> getTagsByNewsId (int newsId) throws DAOException;
}
