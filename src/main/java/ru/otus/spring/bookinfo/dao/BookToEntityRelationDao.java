package ru.otus.spring.bookinfo.dao;

import ru.otus.spring.bookinfo.domain.AbstractEntity;
import ru.otus.spring.bookinfo.domain.Book;

import java.util.List;

public interface BookToEntityRelationDao<E extends AbstractEntity> {

    void insert(Book book, E entity);

    void insert(Book book, List<E> entities);

    void delete(Book book);

    void delete(E entity);

    List<E> getAllForBook(Book book);
}
