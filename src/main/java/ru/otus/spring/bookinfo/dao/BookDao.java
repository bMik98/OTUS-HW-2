package ru.otus.spring.bookinfo.dao;

import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.List;

public interface BookDao extends GenericDao<Book, Integer> {

    List<Book> getByGenre(Genre genre);

    List<Book> getByAuthor(Author author);
}
