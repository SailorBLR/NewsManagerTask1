package by.epam.hubarevich.dao.impl;

import by.epam.hubarevich.dao.AbstractDAO;
import by.epam.hubarevich.dao.RoleDAO;
import by.epam.hubarevich.dao.exceptions.DAOException;
import by.epam.hubarevich.domain.Role;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
@Component
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    BasicDataSource dataSource;

    @Override
    public Set<Role> findAll() throws DAOException {
        return null;
    }

    @Override
    public Role findDomainById(int id) throws DAOException {
        return null;
    }

    @Override
    public void delete(int id) throws DAOException {
    }

    @Override
    public int create(Role domain) throws DAOException {
        return 0;
    }

    @Override
    public void update(Role domain) throws DAOException {
    }

}
