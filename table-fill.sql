INSERT INTO NEWS (NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE)
  SELECT
    LEVEL,
    dbms_random.string ('U', 15),
    dbms_random.string ('U', 20),
    dbms_random.string ('U', 100),
    to_date('2010-01-01', 'yyyy-mm-dd')+dbms_random.value(1,1000),
    to_date('2010-01-01', 'yyyy-mm-dd')+trunc(dbms_random.value(1,1000))
  FROM      dual CONNECT BY LEVEL <= 20
;
INSERT INTO USERS (USER_ID,       USER_NAME, LOGIN,PASSWORD)
    SELECT        LEVEL,       dbms_random.string ('U', 4),
     dbms_random.string ('U', 4),
      dbms_random.string ('U', 4)
    FROM      dual
    CONNECT BY      LEVEL     <= 20     
;
INSERT INTO TAGS (TAG_ID,       TAG_NAME)
    SELECT        LEVEL,       dbms_random.string ('U', 4)
    FROM      dual
    CONNECT BY      LEVEL     <= 20     
;
INSERT INTO AUTHORS (AUTHOR_ID, AUTHOR_NAME)
    SELECT        LEVEL,       dbms_random.string ('U', 4)
    FROM      dual
    CONNECT BY      LEVEL     <= 20     
;
INSERT INTO NEWS_AUTHORS (NEWS_ID, AUTHOR_ID)
    SELECT        LEVEL,       round(dbms_random.value() * 19) + 1
    FROM      dual
    CONNECT BY      LEVEL     <= 20     
;
INSERT INTO NEWS_TAGS (NEWS_ID, TAG_ID)
    SELECT        LEVEL,       round(dbms_random.value() * 19) + 1
    FROM      dual
    CONNECT BY      LEVEL     <= 20     
;
INSERT INTO COMMENTS (NEWS_ID, COMMENT_TEXT, CREATION_DATE)
    SELECT round(dbms_random.value() * 19) + 1, dbms_random.string ('U', 4), CURRENT_TIMESTAMP     
    FROM      dual
    CONNECT BY      LEVEL     <= 20     
;