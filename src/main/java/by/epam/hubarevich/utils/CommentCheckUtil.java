package by.epam.hubarevich.utils;

import by.epam.hubarevich.domain.Comment;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Anton_Hubarevich on 6/27/2016.
 */
public class CommentCheckUtil {
    public static boolean checkComment (Comment comment) {
        if(comment.getCommentText().length()<100||comment.getCommentCreationDate().getTime()<=
                Calendar.getInstance().getTime().getTime()){
            return true;
        }
        return false;
    }
}
