package test.by.epam.hubarevich.service;

import by.epam.hubarevich.dao.CommentDAO;
import by.epam.hubarevich.dao.exception.DAOException;
import by.epam.hubarevich.domain.Comment;
import by.epam.hubarevich.service.exception.LogicException;
import by.epam.hubarevich.service.impl.CommentServiceImpl;
import by.epam.hubarevich.utils.TimestamperUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Anton_Hubarevich on 6/27/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {
    private final Logger LOG = LogManager.getLogger(CommentServiceImplTest.class);

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    CommentDAO commentDAO;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAuthor() {
        try {
            Comment comment = new Comment(1,1, "Name", TimestamperUtil.makeTimeStamp());
            when(commentDAO.create(comment)).thenReturn(1);
            Assert.assertEquals(1, commentService.addComment(comment));
        } catch (DAOException | LogicException e) {
            LOG.error(e);
        }
    }

    @Test (expected = NumberFormatException.class)
    public void testDeleteComment() {
        try {
            doThrow(new NumberFormatException()).when(commentDAO).delete(anyInt());
            commentService.deleteComment(1);
        } catch (DAOException | LogicException e) {
            LOG.error(e);
        }
    }

    @Test (expected = NumberFormatException.class)
    public void testUpdateComment() {
        try {
            Comment comment = new Comment(1,1, "Name", TimestamperUtil.makeTimeStamp());
            doThrow(new NumberFormatException()).when(commentDAO).update(comment);
            commentService.updateComment(comment);
        } catch (DAOException | LogicException e) {
            LOG.error(e);
        }
    }
}
