package ru.otus.spring.bookinfo.service.impl;

import ru.otus.spring.bookinfo.dao.GenericDao;
import ru.otus.spring.bookinfo.service.GenericService;

import java.util.List;

public abstract class AbstractGenericService<E> implements GenericService<E, Integer> {

    private final GenericDao<E, Integer> dao;

    public AbstractGenericService(GenericDao<E, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public void insert(String name) {
        dao.insert(createEntity(name));
    }

    @Override
    public void delete(Integer id) {
        E entity = getById(id);
        if (entity != null) {
            dao.delete(entity);
        }
    }

    @Override
    public E getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public List<E> getAll() {
        return dao.getAll();
    }

    protected abstract E createEntity(String name);
}
