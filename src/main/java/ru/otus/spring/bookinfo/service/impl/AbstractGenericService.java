package ru.otus.spring.bookinfo.service.impl;

import ru.otus.spring.bookinfo.dao.EntityDao;
import ru.otus.spring.bookinfo.domain.AbstractEntity;
import ru.otus.spring.bookinfo.service.GenericService;

import java.util.List;

public abstract class AbstractGenericService<E extends AbstractEntity> implements GenericService<E> {

    private final EntityDao<E> dao;

    public AbstractGenericService(EntityDao<E> dao) {
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
    public void delete(int id) {
        E entity = getById(id);
        if (entity != null) {
            dao.delete(entity);
        }
    }

    @Override
    public E getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<E> getAll() {
        return dao.getAll();
    }

    protected abstract E createEntity(String name);
}
