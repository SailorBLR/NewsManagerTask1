package by.epam.hubarevich.service.impl;

import by.epam.hubarevich.dao.AuthorDAO;
import by.epam.hubarevich.dao.exceptions.DAOException;
import by.epam.hubarevich.dao.impl.AuthorDAOImpl;
import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.service.AuthorService;
import by.epam.hubarevich.service.exception.LogicException;
import by.epam.hubarevich.utils.AuthorCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/24/2016.
 */
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDAO authorDAO = new AuthorDAOImpl();

    @Override
    public int createAuthor(Author author) throws LogicException {
        int authorId = 0;
        try {
            if (AuthorCheckUtil.checkAuthorData(author)) {
                authorId = authorDAO.create(author);
            }
        } catch (DAOException e) {
            throw new LogicException(e);
        }
        return authorId;
    }

    @Override
    public void deleteAuthor(int authorId) throws LogicException {

        try {
            authorDAO.delete(authorId);
        } catch (DAOException e) {
            throw new LogicException();
        }
    }

    @Override
    public void updateAuthor(Author author) throws LogicException {
        try {
            if (AuthorCheckUtil.checkAuthorData(author)) {
                authorDAO.update(author);
            }
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public Author getAuthorByNewsId(int newsId) throws LogicException {
        Author author;
        try {
            author = authorDAO.findAuthorByNewsId(newsId);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
        return author;
    }

    @Override
    public Set<Author> getListOfAuthors() throws LogicException {
        Set<Author> authors;
        try {
            authors=authorDAO.findAll();
        } catch (DAOException e) {
            throw new LogicException(e);
        }
        return authors;
    }
}
