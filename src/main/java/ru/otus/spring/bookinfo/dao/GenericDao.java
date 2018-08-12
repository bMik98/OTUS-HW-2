package ru.otus.spring.bookinfo.dao;

import java.util.List;

public interface GenericDao<E, K> {

    int count();

    void insert(E entity);

    void delete(E entity);

    E getById(K id);

    List<E> getAll();
}
