package by.epam.hubarevich.dao;

import by.epam.hubarevich.dao.exceptions.DAOException;
import by.epam.hubarevich.domain.Domain;
import org.apache.commons.dbcp.BasicDataSource;
import java.util.List;
import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/17/2016.
 */
public interface AbstractDAO <T extends Domain> {

/*
    private BasicDataSource dataSource;
*/

    /**
     * Sets the data source
     * @param dataSource
     */

  /*  public void setDataSource(BasicDataSource dataSource) {

        this.dataSource = dataSource;
    }*/
    /**
     * @return List of all Objects Domain from database
     * @throws DAOException if SQLException thrown
     */
    Set<T> findAll() throws DAOException;

    /**
     * finds the Domain by it's id
     * @param id unique Domain identification number
     * @return Domain object
     * @throws DAOException if SQLException thrown
     */
    T findDomainById(int id) throws DAOException;

    /**
     * deletes Domain by it's ID
     * @param id unique Domain identification number
     * @return true if the deleting is successfully
     * @throws DAOException if SQLException thrown
     */
    void delete(int id) throws DAOException;

    /**
     * deletes the field in DB by Object class Domain
     * @param domain Object class T extends Domain
     * @return true if the deleting is successfully
     * @throws DAOException if SQLException thrown
     */

    int create(T domain) throws DAOException;

    /**
     * updates a field in DB by Object class extends Domain
     * @param domain Object class T extends Domain
     * @return true if the updating is successfully
     * @throws DAOException if SQLException thrown
     */
    void update(T domain) throws DAOException;

    /**
     * finds a field in DB by Object class extends Domain
     * @param domain Object class T extends Domain
     * @return Domain object
     * @throws DAOException if SQLException thrown
     */
}
