package by.epam.hubarevich.utils;

import by.epam.hubarevich.domain.Author;

/**
 * Created by Anton_Hubarevich on 6/27/2016.
 */
public class AuthorCheckUtil {
    public static boolean checkAuthorData (Author author) {
        if(author.getAuthorId()<1||author.getAuthorName().length()>30||
                author.getAuthorName()==null||author.getAuthorId()==null){
            return false;
        }
        return true;
    }
}
