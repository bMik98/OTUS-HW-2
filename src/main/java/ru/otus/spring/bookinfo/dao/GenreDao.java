package ru.otus.spring.bookinfo.dao;

import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.List;

public interface GenreDao extends BasicEntityDao<Genre> {

    List<Genre> getByBook(Book book);
}
