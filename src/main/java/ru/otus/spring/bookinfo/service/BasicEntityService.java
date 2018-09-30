package ru.otus.spring.bookinfo.service;

import ru.otus.spring.bookinfo.dao.BasicEntityDao;
import ru.otus.spring.bookinfo.domain.BasicEntity;

import java.util.List;

public abstract class BasicEntityService<E extends BasicEntity> {

    protected final BasicEntityDao<E> dao;

    BasicEntityService(BasicEntityDao<E> dao) {
        this.dao = dao;
    }

    public int count() {
        return dao.count();
    }

    public void save(E entity) {
        dao.save(entity);
    }

    public void delete(E entity) {
        dao.delete(entity);
    }

    public E getById(int id) {
        return dao.getById(id);
    }

    public List<E> getAll() {
        return dao.getAll();
    }
}
