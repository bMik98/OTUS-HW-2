package ru.otus.spring.bookinfo.dao.jdbc;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.domain.Author;

@Repository
public class JdbcAuthorDao extends AbstractJdbcEntityDao<Author> implements AuthorDao {

    public JdbcAuthorDao(JdbcOperations jdbcOperations) {
        super(jdbcOperations);
    }

    @Override
    public String getEntityTableName() {
        return "authors";
    }

    @Override
    public RowMapper<Author> createEntityMapper() {
        return (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return new Author(id, name);
        };
    }
}
