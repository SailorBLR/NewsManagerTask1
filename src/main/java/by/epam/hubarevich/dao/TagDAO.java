package by.epam.hubarevich.dao;

import by.epam.hubarevich.dao.exceptions.DAOException;
import by.epam.hubarevich.domain.Tag;

import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
public interface TagDAO extends AbstractDAO<Tag> {
    Set<Tag> getTagsByNewsId (int newsId) throws DAOException;
}
