package ru.otus.spring.bookinfo.dao;

import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

public interface BookDao extends EntityDao<Book> {

    void unbound(Book book, Author author);

    void unbound(Book book, Genre genre);

    void bound(Book book, Author author);

    void bound(Book book, Genre genre);
}
