package by.epam.hubarevich.dao;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
public enum QueryList {

    CREATE_NEW_AUTHOR("INSERT INTO authors (author_name) VALUES (?)"),
    DELETE_AUTHOR("DELETE FROM authors WHERE author_id = ?"),
    UPDATE_AUTHOR("UPDATE authors SET author_name = ?,expired = ? where author_id = ?"),
    FIND_AUTHOR_BY_ID("SELECT author_name,author_id,expired FROM authors WHERE author_id=?"),
    FIND_AUTHOR_BY_NAME("SELECT author_name,author_id,expired FROM authors WHERE author_name=?"),
    FIND_AUTHORS_BY_NEWS_ID("SELECT A.author_id,A.author_name,A.expired FROM authors A LEFT OUTER JOIN news_authors NA " +
            "ON NA.author_id = A.AUTHOR_ID LEFT OUTER JOIN news N ON N.news_id=NA.news_id WHERE N.news_id=?"),
    FIND_ALL_AUTHORS("SELECT author_id,author_name,expired FROM authors"),
    CREATE_NEWS("INSERT INTO news (title,short_text,full_text,creation_date,modification_date) VALUES (?,?,?,?,?)"),
    DELETE_NEWS("DELETE FROM news WHERE news_id = ?"),
    UPDATE_NEWS("UPDATE news SET title=?,short_text=?,full_text=?,modification_date=? WHERE news_id=?"),
    FIND_NEWS_BY_ID("SELECT news_id,title,short_text,full_text,creation_date,modification_date FROM news WHERE news_id = ?"),
    FIND_ALL_NEWS("SELECT news_id,title,short_text,full_text,creation_date,modification_date FROM news"),
    ADD_AUTHOR_TO_NEWS("INSERT INTO news_authors (news_id,author_id) VALUES (?,?)"),
    GET_MOST_COMMENTED_NEWS("SELECT * FROM (SELECT news.news_id,news.title, news.short_text, news.full_text," +
            "            news.creation_date, news.modification_date, NVL(num_comments, 0) AS num_comments FROM      news " +
            "            LEFT JOIN (SELECT   news_id, COUNT(*) AS num_comments FROM     comments GROUP BY news_id) cmt on cmt.news_id = news.news_id " +
            "            ORDER BY  num_comments DESC) WHERE ROWNUM<=?"),
    FIND_NEWS_BY_AUTHOR("SELECT N.news_id,N.title,N.short_text,N.full_text,N.creation_date,N.modification_date FROM news N " +
            "LEFT JOIN news_authors NA on NA.news_id=N.news_id WHERE NA.author_id=?"),
    FIND_NEWS_BY_TAGS("SELECT N.news_id,N.title,N.short_text,N.full_text,N.creation_date,N.modification_date FROM news N " +
            "            LEFT JOIN news_tags NT on NT.news_id=N.news_id  WHERE NT.tag_id=?"),
    ADD_TAG_TO_NEWS("INSERT INTO news_tags (news_id,tag_id) VALUES (?,?)"),
    FIND_TAGS_BY_NEWS_ID("SELECT T.TAG_ID,T.TAG_NAME FROM tags T LEFT JOIN news_tags NT on NT.TAG_ID = T.TAG_ID WHERE NT.NEWS_ID=?"),
    FIND_ALL_COMMENTS("SELECT comment_id,news_id,comment_text,creation_date FROM comments"),
    FIND_COMMENT_BY_ID("SELECT comment_id,news_id,comment_text,creation_date FROM comments WHERE comment_id=?"),
    FIND_COMMENT_BY_NEWS_ID("SELECT comment_id,news_id,comment_text,creation_date FROM comments WHERE news_id=?"),
    DELETE_COMMENT("DELETE FROM comments WHERE comment_id=?"),
    ADD_COMMENT_TO_NEWS("INSERT INTO comments (news_id,comment_text,creation_date) VALUES (?,?,?)"),
    UPDATE_COMMENT("UPDATE comments SET comment_text=? WHERE comment_id=?"),
    ANOTHER_TAG("OR NT.tag_id=? ");


    private String value;

    QueryList(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;

    }
}
