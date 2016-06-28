package by.epam.hubarevich.utils;

import by.epam.hubarevich.domain.Tag;
import by.epam.hubarevich.domain.dto.NewsDTO;
import by.epam.hubarevich.domain.dto.SearchDTO;

import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/23/2016.
 */
public class NewsCheckUtil {

    public static boolean checkNewsDto(NewsDTO newsDTO) {

        boolean success = true;
        if ((newsDTO.getNews().getNewsCreationDate().getTime()
                >newsDTO.getNews().getNewsModificationDate().getTime())||
                (newsDTO.getNews().getTitle().length()>30)||
                (newsDTO.getNews().getShortText().length()>100)||
                (newsDTO.getNews().getFullText().length()>2000)||
                newsDTO.getAuthor().getAuthorName().length()>30) {
            success = false;
        }

        for (Tag tag : newsDTO.getTags()) {
            if (tag.getTagName().length()>30) {
                success=false;
            }
        }
        return success;
    }


    public static boolean checkSearchDto(SearchDTO searchDto){
        boolean success = true;
        if (searchDto.getAuthor()==null&searchDto.getTags()==null){
            success = false;
        }
        if (searchDto.getTags()!=null) {
            for (Tag tag : searchDto.getTags()) {
                if (tag.getTagName().length() > 30) {
                    success = false;
                }
            }
        }
        if ((searchDto.getAuthor()!=null)&&searchDto.getAuthor().getAuthorId()<1) {
            success = false;
        }
        return success;
    }
}
