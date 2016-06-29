package test.by.epam.hubarevich.service;

import by.epam.hubarevich.dao.AuthorDAO;
import by.epam.hubarevich.dao.exception.DAOException;
import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.service.exception.LogicException;
import by.epam.hubarevich.service.impl.AuthorServiceImpl;
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

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Anton_Hubarevich on 6/24/2016.
 */
@RunWith(MockitoJUnitRunner.class)

public class AuthorServiceImpTest {
    private final Logger LOG = LogManager.getLogger(AuthorServiceImpTest.class);

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    AuthorDAO authorDAO;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAuthor() {
        try {
            Author author = new Author(1, "Name");
            when(authorDAO.create(author)).thenReturn(1);
            Assert.assertEquals(1, authorService.createAuthor(author));
        } catch (DAOException | LogicException e) {
            LOG.error(e);
        }
    }

    @Test (expected = NumberFormatException.class)
    public void testDeleteAuthor() {
        try {
            doThrow(new NumberFormatException()).when(authorDAO).delete(anyInt());
            authorService.deleteAuthor(anyInt());
        } catch (DAOException | LogicException e) {
            LOG.error(e);
        }
    }

    @Test (expected = NumberFormatException.class)
    public void testUpdateAuthor() {
        try {
            Author author = new Author(1, "Name");
            doThrow(new NumberFormatException()).when(authorDAO).update(author);
            authorService.updateAuthor(author);
        } catch (DAOException | LogicException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testGetAuthorByNewsId() {
        try {
            Author author = new Author(1, "Name");
            when(authorDAO.findAuthorByNewsId(1)).thenReturn(author);
            Assert.assertEquals(author, authorService.getAuthorByNewsId(1));
        } catch (DAOException | LogicException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testGetListOfAuthors() {
        try {
            Set<Author> authors = new HashSet<>();
            Author author = new Author(1, "Name");
            Author author2 = new Author(2, "Name2");
            Author author3 = new Author(3, "Name3");
            authors.add(author);
            authors.add(author2);
            authors.add(author3);
            when(authorDAO.findAll()).thenReturn(authors);
            Assert.assertEquals(authors, authorService.getListOfAuthors());
        } catch (DAOException | LogicException e) {
            LOG.error(e);
        }
    }
}
