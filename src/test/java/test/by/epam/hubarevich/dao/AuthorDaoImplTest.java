package test.by.epam.hubarevich.dao;

import by.epam.hubarevich.dao.exception.DAOException;
import by.epam.hubarevich.dao.impl.AuthorDAOImpl;
import by.epam.hubarevich.domain.Author;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-test.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)


public class AuthorDaoImplTest {
    private static final Logger LOG = LogManager.getLogger(AuthorDaoImplTest.class);

    @Autowired
    private AuthorDAOImpl authorDAO;

    @Test
    public void testCreate() {
        try {
            Author author = new Author();
            author.setAuthorName("Test");
            Assert.assertNotEquals(0, authorDAO.create(author));
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testDelete() {
        try {
            authorDAO.delete(1);
            Assert.assertEquals(authorDAO.findDomainById(1), null);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testUpdate() {
        try {
            Author author = new Author(1,"Ivan Ivanov");
            author.setExpired(Timestamp.valueOf("2016-06-20 20:15:11"));
            authorDAO.update(author);
            Assert.assertEquals(authorDAO.findDomainById(1), author);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }


    @Test
    public void testFindAuthorById() {
        try {
            Author author = new Author();
            author.setAuthorId(1);
            Assert.assertEquals(authorDAO.findDomainById(author.getAuthorId())
                    .getAuthorId(), author.getAuthorId());
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testFindAuthorByName() {
        try {
            Author author = new Author();
            author.setAuthorName("Ivan Ivanov");
            Assert.assertEquals(authorDAO.findAuthorByName(author.getAuthorName()).getAuthorName(),
                    author.getAuthorName());
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testFindAuthorByNewsId() {
        try {
            Author author = new Author(1,"Ivan Ivanov");
            Assert.assertEquals(authorDAO.findAuthorByNewsId(1),
                    author);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testFindAllAuthors() {
        try {
            Set<Author> authors = new HashSet<Author>();
            Author author = new Author(1,"Ivan Ivanov");
            Author author1 = new Author(2,"Pavel Pavlov");
            Author author2 = new Author(3,"Kto-to");
            authors.add(author);
            authors.add(author1);
            authors.add(author2);
            assertThat(authorDAO.findAll().containsAll(authors));

        } catch (DAOException e) {
            LOG.error(e);
        }
    }

}
