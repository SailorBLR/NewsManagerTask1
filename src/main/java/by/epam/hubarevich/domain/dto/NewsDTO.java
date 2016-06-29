package by.epam.hubarevich.domain.dto;

import by.epam.hubarevich.domain.Comment;
import by.epam.hubarevich.domain.News;
import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.domain.Tag;

import java.util.Set;

/**
 * Class represents Data Transfer Object
 * Used to transfer data corresponded to News message
 * @author Anton_Hubarevich
 * @version 1.0
 */
public class NewsDTO {

    /**
     * News message object
     */
    private News news;
    /**
     * Author object
     */
    private Author author;
    /**
     * Set of Tag objects
     */
    private Set<Tag> tags;

    /**
     * Set of Comment objects
     */

    private Set<Comment> comments;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
