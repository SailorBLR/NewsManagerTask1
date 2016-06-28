package by.epam.hubarevich.domain.dto;

import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.domain.Tag;

import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
public class SearchDTO {
    private Set<Tag> tags;
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
