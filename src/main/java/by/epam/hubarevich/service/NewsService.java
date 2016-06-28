package by.epam.hubarevich.service;

import by.epam.hubarevich.domain.dto.NewsDTO;
import by.epam.hubarevich.domain.dto.SearchDTO;
import by.epam.hubarevich.service.exception.LogicException;

import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/23/2016.
 */
public interface NewsService {
    int createNews (NewsDTO newsDTO) throws LogicException;
    void deleteNews (int newsId) throws LogicException;
    Set<NewsDTO> searchForNews (SearchDTO searchDTO) throws LogicException;
    int updateNews (NewsDTO newsDTO) throws LogicException;
    Set<NewsDTO> searchForNewsCommented (boolean mostCommented,int quantityMoastCommented)  throws LogicException;
}
