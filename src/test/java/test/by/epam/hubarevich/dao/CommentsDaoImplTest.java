package test.by.epam.hubarevich.dao;

import by.epam.hubarevich.dao.impl.CommentDAOImpl;
import by.epam.hubarevich.dao.impl.NewsDAOImpl;
import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.domain.Comment;
import by.epam.hubarevich.domain.News;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anton_Hubarevich on 6/23/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:beans-test.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
public class CommentsDaoImplTest {

    @Autowired
    CommentDAOImpl commentDao = new CommentDAOImpl();
    @Autowired
    NewsDAOImpl newsDAO = new NewsDAOImpl();

    @Transactional
    @Rollback(true)
    @Test
    public void testCreate() throws Exception {

        News news = new News();
        news.setTitle("T");
        news.setShortText("ST");
        news.setFullText("S");
        news.setNewsCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        news.setNewsModificationDate(new Date(Calendar.getInstance().getTime().getTime()));
        newsDAO.create(news);
        Comment comment = new Comment();
        comment.setNewsId(newsDAO.findAll().iterator().next().getNewsId());
        comment.setCommentText("Something");
        comment.setCommentCreationDate(Timestamp.valueOf("2016-06-20 20:15:11"));
        Assert.assertNotEquals(0, commentDao.create(comment));
    }


    @Test
    public void testDelete () throws Exception {
        commentDao.delete(1);
        Assert.assertEquals(commentDao.findDomainById(1),null);
    }
    @Test
    public void testUpdate () throws Exception {
        Comment comment = new Comment(1,1,"SomethingElse",Timestamp.valueOf("2016-06-20 20:15:11"));
        commentDao.update(comment);
        Assert.assertEquals(commentDao.findDomainById(1),comment);
    }
    @Test
    public void testFindAll () throws Exception {
        Set<Comment> comments = new HashSet<>();
        comments.add(commentDao.findDomainById(1));
        comments.add(commentDao.findDomainById(2));
        comments.add(commentDao.findDomainById(3));
        assertThat(commentDao.findAll().contains(comments));
    }

    @Test
    public void testFindCommentsByNewsId () throws Exception {
        Set<Comment> comments = new HashSet<>();
        comments.add(commentDao.findDomainById(1));
        comments.add(commentDao.findDomainById(2));
        assertThat(commentDao.findCommentsByNewsId(1).contains(comments));
    }
}

