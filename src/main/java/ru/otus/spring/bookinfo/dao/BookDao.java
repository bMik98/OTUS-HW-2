package ru.otus.spring.bookinfo.dao;

import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

public interface BookDao extends BasicEntityDao<Book> {

    void unbind(Book book, Author author);

    void unbind(Book book, Genre genre);

    void bind(Book book, Author author);

    void bind(Book book, Genre genre);
}
