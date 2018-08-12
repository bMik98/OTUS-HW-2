package ru.otus.spring.bookinfo.dao;

import java.util.List;

public interface GenericDao<E> {

    int count();

    void insert(E entity);

    void delete(E entity);

    E getById(int id);

    List<E> getAll();
}
