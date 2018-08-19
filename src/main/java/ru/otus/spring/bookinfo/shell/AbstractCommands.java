package ru.otus.spring.bookinfo.shell;

import ru.otus.spring.bookinfo.dao.EntityDao;
import ru.otus.spring.bookinfo.domain.AbstractEntity;

public abstract class AbstractCommands<E extends AbstractEntity> {
    protected final EntityDao<E> dao;

    AbstractCommands(EntityDao<E> dao) {
        this.dao = dao;
    }

    void showCount() {
        System.out.println(dao.count());
    }

    void insertEntity(String name) {
        dao.insert(createEntity(name));
        listEntities();
    }

    void deleteEntity(int id) {
        E entity = dao.getById(id);
        dao.delete(entity);
        listEntities();
    }

    void getEntity(int id) {
        showTitle();
        E entity = dao.getById(id);
        showEntity(entity);
    }

    void listEntities() {
        showTitle();
        dao.getAll().forEach(this::showEntity);
    }

    void showTitle() {
        System.out.println("ID        Name");
        System.out.println("--------- --------------------------------------------------");
    }

    void showEntity(E entity) {
        System.out.printf("%9d %-50s %n", entity.getId(), entity.getName());
    }

    protected abstract E createEntity(String name);
}