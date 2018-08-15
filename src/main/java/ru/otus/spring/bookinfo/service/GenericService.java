package ru.otus.spring.bookinfo.service;

import java.util.List;

public interface GenericService<E> {

    int count();

    void insert(String name);

    void delete(int id);

    E getById(int id);

    List<E> getAll();
}
