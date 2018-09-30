package ru.otus.spring.bookinfo.dao;

import ru.otus.spring.bookinfo.domain.BasicEntity;

import java.util.List;

public interface BasicEntityDao<E extends BasicEntity> {

    int count();

    void save(E entity);

    void delete(E entity);

    E getById(int id);

    List<E> getAll();
}
