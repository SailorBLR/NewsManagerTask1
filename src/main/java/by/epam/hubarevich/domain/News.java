package by.epam.hubarevich.domain;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Class used to represent News message entity
 * @author Anton_Hubarevich
 * @version 1.0
 */

public class News extends Domain {
    private static final long serialVersionUID = 1L;
    /**
     * News message identifier
     */
    private Integer newsId;
    /**
     * News message title
     */
    private String title;
    /**
     * News message short text message
     */
    private String shortText;
    /**
     * News message full text message
     */
    private String fullText;
    /**
     * News message creation date
     */
    private Timestamp newsCreationDate;
    /**
     * News message modification date
     */
    private Date newsModificationDate;

    public News() {
    }

    /**
     * News messge constructor
     * @param newsId positive Integer value
     * @param title String value. Limit 30 symbols
     * @param shortText String value. Limit 100 symbols
     * @param fullText String value. Limit 2000 symbols
     * @param newsCreationDate Timestamp value. News message creation date
     * @param newsModificationDate Date value. News message modification date
     */
    public News(Integer newsId, String title, String shortText, String fullText,
                Timestamp newsCreationDate, Date newsModificationDate) {
        this.newsId = newsId;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.newsCreationDate = newsCreationDate;
        this.newsModificationDate = newsModificationDate;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public Timestamp getNewsCreationDate() {
        return newsCreationDate;
    }

    public void setNewsCreationDate(Timestamp newsCreationDate) {
        this.newsCreationDate = newsCreationDate;
    }

    public Date getNewsModificationDate() {
        return newsModificationDate;
    }

    public void setNewsModificationDate(Date newsModificationDate) {
        this.newsModificationDate = newsModificationDate;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", shortText='" + shortText + '\'' +
                ", fullText='" + fullText + '\'' +
                ", newsCreationDate=" + newsCreationDate +
                ", newsModificationDate=" + newsModificationDate +
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

        News news = (News) o;

        if (!newsId.equals(news.newsId)){
            return false;
        }
        if (!title.equals(news.title)){
            return false;
        }
        if (!shortText.equals(news.shortText)) {
            return false;
        }
        if (!fullText.equals(news.fullText)) {
            return false;
        }
        if (!newsCreationDate.equals(news.newsCreationDate)) {
            return false;
        }
        return newsModificationDate.equals(news.newsModificationDate);

    }

    @Override
    public int hashCode() {
        int result = newsId.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + shortText.hashCode();
        result = 31 * result + fullText.hashCode();
        result = 31 * result + newsCreationDate.hashCode();
        result = 31 * result + newsModificationDate.hashCode();
        return result;
    }
}

