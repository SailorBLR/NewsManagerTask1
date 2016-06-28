package by.epam.hubarevich.service;

import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.service.exception.LogicException;

import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/23/2016.
 */
public interface AuthorService {
    int createAuthor(Author author) throws LogicException;
    void deleteAuthor(int authorId) throws LogicException;
    void updateAuthor (Author author) throws LogicException;
    Author getAuthorByNewsId (int newsId) throws LogicException;
    Set<Author> getListOfAuthors () throws LogicException;
}
