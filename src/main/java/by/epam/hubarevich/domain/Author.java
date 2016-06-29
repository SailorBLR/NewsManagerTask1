package by.epam.hubarevich.domain;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import java.sql.Timestamp;

/**
 * Class used to represent Author entity
 * @author Anton_Hubarevich
 * @version 1.0
 */

public class Author extends Domain {
    private static final long serialVersionUID = 1L;

    /**
     * Author identifier
     */
    @Required
    private Integer authorId;
    /**
     * Author name
     */
    @Required
    private String authorName;

    /**
     * Author expiration date
     * NULL if is not expired
     */
    private Timestamp expired;

    public Author() {
    }

    /**
     * Author constructor
     * @param authorId positive Integer identifier
     * @param authorName String value. Limit 30 symbols
     */

    public Author(Integer authorId, String authorName) {
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", expired=" + expired +
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

        Author author = (Author) o;

        if (!authorId.equals(author.authorId)) {
            return false;
        }
        if (!authorName.equals(author.authorName)) {
            return false;
        }
        return !(expired != null ? !expired.equals(author.expired) : author.expired != null);

    }

    @Override
    public int hashCode() {
        int result = authorId.hashCode();
        result = 31 * result + authorName.hashCode();
        result = 31 * result + (expired != null ? expired.hashCode() : 0);
        return result;
    }
}
