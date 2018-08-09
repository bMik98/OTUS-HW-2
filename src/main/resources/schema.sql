DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(ID INT PRIMARY KEY, NAME VARCHAR(255));

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES(ID INT PRIMARY KEY, NAME VARCHAR(255));

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(ID INT PRIMARY KEY, TITLE VARCHAR(255));

DROP TABLE IF EXISTS BOOK_AUTHORS;
CREATE TABLE BOOK_AUTHORS(AUTHOR_ID INT, BOOK_ID INT);

DROP TABLE IF EXISTS BOOK_GENRES;
CREATE TABLE BOOK_GENRES(GENRE_ID INT, BOOK_ID INT);
