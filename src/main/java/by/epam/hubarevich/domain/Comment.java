package by.epam.hubarevich.domain;

import java.sql.Timestamp;

/**
 * Class used to represent Comment entity
 * @author Anton_Hubarevich
 * @version 1.0
 */

public class Comment extends Domain {
    private static final long serialVersionUID = 1L;

    /**
     * Comment identifier
     */
    private Integer commentId;
    /**
     * News message identifier
     */
    private Integer newsId;
    /**
     * Comment text
     */
    private String commentText;
    /**
     * Comment creation date
     */
    private Timestamp commentCreationDate;

    public Comment() {
    }

    /**
     * Comment constructor
     * @param commentId positive Integer value
     * @param newsId positive Integer value
     * @param commentText String value. Limit 100 symbols
     * @param commentCreationDate Timestamp value
     */
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
