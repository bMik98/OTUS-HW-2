package ru.otus.spring.bookinfo.dao;

import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;

import java.util.List;

public interface AuthorDao extends EntityDao<Author> {

    List<Author> getByBook(Book book);
}
