package by.epam.hubarevich.service;

import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.service.exception.LogicException;

import java.util.Set;

/**
 * Class for Author Service operations
 * @author Anton_Hubarevich
 * @version 1.0
 */
public interface AuthorService {
    /**
     * Creates Author object
     * @param author Author instance
     * @return Author identifier
     * @throws LogicException if DAOException obtained
     */
    int createAuthor(Author author) throws LogicException;

    /**
     * Deletes field from database
     * @param authorId Integer value
     * @throws LogicException if DAOException obtained
     */
    void deleteAuthor(int authorId) throws LogicException;
    void updateAuthor (Author author) throws LogicException;
    Author getAuthorByNewsId (int newsId) throws LogicException;
    Set<Author> getListOfAuthors () throws LogicException;
}
