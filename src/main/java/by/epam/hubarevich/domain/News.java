package by.epam.hubarevich.domain;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
public class News extends Domain {
    private Integer newsId;
    private String title;
    private String shortText;
    private String fullText;
    private Timestamp newsCreationDate;
    private Date newsModificationDate;

    public News() {
    }

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

