package by.epam.hubarevich.dao;

import by.epam.hubarevich.dao.exceptions.DAOException;
import by.epam.hubarevich.domain.Author;

import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
public interface AuthorDAO extends AbstractDAO <Author>{

    Author findAuthorByName (String authorName) throws DAOException;
    Author findAuthorByNewsId (int newsId) throws DAOException;
}
