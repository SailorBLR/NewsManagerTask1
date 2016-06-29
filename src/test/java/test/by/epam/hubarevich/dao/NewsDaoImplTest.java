package test.by.epam.hubarevich.dao;

import by.epam.hubarevich.dao.exception.DAOException;
import by.epam.hubarevich.dao.impl.AuthorDAOImpl;
import by.epam.hubarevich.dao.impl.NewsDAOImpl;
import by.epam.hubarevich.dao.impl.TagDAOImpl;
import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.domain.News;
import by.epam.hubarevich.domain.Tag;
import by.epam.hubarevich.utils.TimestamperUtil;
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
import static org.junit.Assert.assertEquals;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/21/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-test.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)

public class NewsDaoImplTest {

    private final Logger LOG = LogManager.getLogger(NewsDaoImplTest.class);

    @Autowired
    NewsDAOImpl newsDAO = new NewsDAOImpl();
    @Autowired
    AuthorDAOImpl authorDAO = new AuthorDAOImpl();
    @Autowired
    TagDAOImpl tagDAO = new TagDAOImpl();


    @Test
    public void testCreate() {
        try {
            Date date = new Date(Calendar.getInstance().getTime().getTime());
            News news = new News(1, "Test", "STExt", "FText", Timestamp.valueOf(LocalDateTime.now()),
                    date);
            Assert.assertNotEquals(0, newsDAO.create(news));
        } catch (DAOException e) {
            LOG.error(e);
        }
    }


    @Test
    public void testDelete() {
        try {
            newsDAO.delete(1);
            assertEquals(newsDAO.findDomainById(1), null);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }


    @Test
    public void testUpdate() {
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        News news;
        try {
            news = new News(1, "Test", "STExt", "FText", newsDAO.findDomainById(1).getNewsCreationDate(),
                    date);
            newsDAO.update(news);
            assertEquals(newsDAO.findDomainById(1).toString().trim(), news.toString().trim());
        } catch (DAOException e) {
            LOG.error(e);
        }
    }


    @Test
    public void testFindDomainById() {
        try {
            News news = new News();
            news.setNewsId(1);
            assertEquals(newsDAO.findDomainById(1).getNewsId(), news.getNewsId());
        } catch (DAOException e) {
            LOG.error(e);
        }
    }


    @Test
    public void testFindAll() {
        try {
            Set<News> newses = new HashSet<News>();
            newses.add(newsDAO.findDomainById(1));
            newses.add(newsDAO.findDomainById(2));
            newses.add(newsDAO.findDomainById(3));
            assertThat(newsDAO.findAll()).isEqualTo(newses);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testAddNewsAuthors() {
        try {
            Author author = new Author(1, "Ivan Ivanov");
            newsDAO.addNewsAuthor(1, 1);
            assertEquals(authorDAO.findAuthorByNewsId(1), author);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }


    @Test
    public void testGetMostCommentedNews() {

        try {
            Set<News> newses = new HashSet<News>();
            newses.add(newsDAO.findDomainById(1));
            newses.add(newsDAO.findDomainById(2));
            assertThat(newsDAO.getMostCommentedNews(2).contains(newses));
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testAddTagsNews() {
        try {
            Set<Tag> tags = new HashSet<>();
            Tag tag = new Tag(1, "Religion");
            tags.add(tag);
            tag = new Tag(2, "Belarus");
            tags.add(tag);
            newsDAO.addTagsNews(1, tags);
            assertThat(tagDAO.getTagsByNewsId(1).contains(tags));
        } catch (DAOException e) {
            LOG.error(e);
        }
    }


    @Test
    public void testFindNewsByTags() {
        try {
            Set<News> newses = new HashSet<>();
            Set<Tag> tags = new HashSet<>();
            Tag tag = new Tag(1, "Religion");
            tags.add(tag);
            tag = new Tag(2, "Belarus");
            tags.add(tag);
            newses.add(newsDAO.findDomainById(1));
            newses.add(newsDAO.findDomainById(2));
            assertThat(newsDAO.findNewsByTags(tags).contains(newses));
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testFindNewsByAuthor() {
        try {
            Set<News> newses = new HashSet<>();
            Author author = new Author(1, "Ivan Ivanov");
            newses.add(newsDAO.findDomainById(1));
            newses.add(newsDAO.findDomainById(3));
            assertThat(newsDAO.findNewsByAuthor(author).contains(newses));
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

}
