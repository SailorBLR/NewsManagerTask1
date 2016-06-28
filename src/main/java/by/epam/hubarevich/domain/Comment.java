package by.epam.hubarevich.domain;

import java.sql.Timestamp;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
public class Comment extends Domain {

    private Integer commentId;
    private Integer newsId;
    private String commentText;
    private Timestamp commentCreationDate;

    public Comment() {
    }

    public Comment(Integer commentId, Integer newsId, String commentText, Timestamp commentCreationDate) {
        this.commentId = commentId;
        this.newsId = newsId;
        this.commentText = commentText;
        this.commentCreationDate = commentCreationDate;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getCommentCreationDate() {
        return commentCreationDate;
    }

    public void setCommentCreationDate(Timestamp commentCreationDate) {
        this.commentCreationDate = commentCreationDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", newsId=" + newsId +
                ", commentText='" + commentText + '\'' +
                ", commentCreationDate=" + commentCreationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Comment comment = (Comment) o;

        if (!commentId.equals(comment.commentId)) {
            return false;
        }
        if (!newsId.equals(comment.newsId)) {
            return false;
        }
        if (!commentText.equals(comment.commentText)){
            return false;
        }
        return commentCreationDate.equals(comment.commentCreationDate);

    }

    @Override
    public int hashCode() {
        int result = commentId.hashCode();
        result = 31 * result + newsId.hashCode();
        result = 31 * result + commentText.hashCode();
        result = 31 * result + commentCreationDate.hashCode();
        return result;
    }
}
