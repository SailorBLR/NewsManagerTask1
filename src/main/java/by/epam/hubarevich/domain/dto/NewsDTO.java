package by.epam.hubarevich.domain.dto;

import by.epam.hubarevich.domain.Comment;
import by.epam.hubarevich.domain.News;
import by.epam.hubarevich.domain.Author;
import by.epam.hubarevich.domain.Tag;

import java.util.Set;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
public class NewsDTO {

    private News news;
    private Author author;
    private Set<Tag> tags;
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
