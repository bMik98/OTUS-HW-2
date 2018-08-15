package ru.otus.spring.bookinfo.dao;

import ru.otus.spring.bookinfo.domain.AbstractEntity;

import java.util.List;

public interface EntityDao<E extends AbstractEntity> {

    int count();

    void insert(E entity);

    void delete(E entity);

    E getById(int id);

    List<E> getAll();
}
