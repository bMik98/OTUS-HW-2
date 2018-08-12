package ru.otus.spring.bookinfo.service;

import java.util.List;

public interface GenericService<E, K> {

    int count();

    void insert(String name);

    void delete(K id);

    E getById(K id);

    List<E> getAll();
}
