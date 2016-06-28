package by.epam.hubarevich.service;

import by.epam.hubarevich.domain.Comment;
import by.epam.hubarevich.service.exception.LogicException;
import javafx.fxml.LoadException;

/**
 * Created by Anton_Hubarevich on 6/27/2016.
 */
public interface CommentService {
    int addComment (Comment comment) throws LogicException;
    void deleteComment(int commentId) throws LogicException;
    void updateComment (Comment comment) throws LogicException;
}
