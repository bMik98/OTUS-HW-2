package ru.otus.spring.bookinfo.dao.jdbc;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.domain.Book;

@Repository
public class JdbcBookDao extends AbstractJdbcEntityDao<Book> implements BookDao {

    public JdbcBookDao(JdbcOperations jdbcOperations) {
        super(jdbcOperations);
    }

    @Override
    public String getEntityTableName() {
        return "books";
    }

    @Override
    public RowMapper<Book> createEntityMapper() {
        return (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return new Book(id, name);
        };
    }
}
