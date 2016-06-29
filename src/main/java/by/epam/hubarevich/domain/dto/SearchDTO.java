package by.epam.hubarevich.domain.dto;

import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.domain.Tag;

import java.util.Set;

/**
 * Class represents Search Criteria Object
 * Serves to choose the search method for service layer
 * @author Anton_Hubarevich
 * @version 1.0
 * @see Tag
 * @see Author
 */
public class SearchDTO {
    /**
     * Set of Tags to search
     */
    private Set<Tag> tags;

    /**
     * Author to search
     */
    private Author author;


    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
