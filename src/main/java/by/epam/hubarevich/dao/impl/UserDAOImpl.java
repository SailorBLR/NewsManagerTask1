package by.epam.hubarevich.dao.impl;

import by.epam.hubarevich.dao.UserDAO;
import by.epam.hubarevich.dao.exception.DAOException;
import by.epam.hubarevich.domain.User;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Implementation of UserDAO. Contains methods realisations
 * @author Anton_Hubarevich
 * @version 1.0
 */
@Component
public class UserDAOImpl implements UserDAO {
    @Autowired
    private BasicDataSource dataSource;

    @Override
    public Set<User> findAll() throws DAOException {
        return null;
    }

    @Override
    public User findDomainById(int id) throws DAOException {
        return null;
    }

    @Override
    public void delete(int id) throws DAOException {
    }


    @Override
    public int create(User domain) throws DAOException {
        return 0;
    }

    @Override
    public void update(User domain) throws DAOException {
    }
}
