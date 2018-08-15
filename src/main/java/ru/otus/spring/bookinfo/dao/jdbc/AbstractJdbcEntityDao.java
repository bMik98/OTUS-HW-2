package ru.otus.spring.bookinfo.dao.jdbc;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import ru.otus.spring.bookinfo.dao.EntityDao;
import ru.otus.spring.bookinfo.domain.AbstractEntity;

import java.util.List;

public abstract class AbstractJdbcEntityDao<E extends AbstractEntity> implements EntityDao<E> {

    protected final JdbcOperations jdbc;

    public AbstractJdbcEntityDao(JdbcOperations jdbcOperations) {
        jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from " + getEntityTableName(), Integer.class);
    }

    @Override
    public void insert(E entity) {
        jdbc.update("insert into " + getEntityTableName() + " (name) values (?)", entity.getName());
    }

    @Override
    public void delete(E entity) {
        jdbc.update("delete from " + getEntityTableName() + " where id = ?", entity.getId());
    }

    @Override
    public E getById(int id) {
        return jdbc.queryForObject("select * from books where id = ?", new Object[]{id}, createEntityMapper());
    }

    @Override
    public List<E> getAll() {
        return jdbc.query("select * from " + getEntityTableName(), createEntityMapper());
    }

    public abstract String getEntityTableName();

    public abstract RowMapper<E> createEntityMapper();
}
