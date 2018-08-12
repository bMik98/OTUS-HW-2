package ru.otus.spring.bookinfo.dao.jdbc;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcAuthorDao implements AuthorDao {

    private final JdbcOperations jdbc;

    public JdbcAuthorDao(JdbcOperations jdbcOperations) {
        jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from authors", Integer.class);
    }

    @Override
    public void insert(Author author) {
        jdbc.update("insert into authors (name) values (?)", author.getName());
    }

    @Override
    public void delete(Author author) {
        jdbc.update("delete from authors where id = ?", author.getId());
    }

    @Override
    public Author getById(Integer id) {
        return jdbc.queryForObject("select * from authors where id = ?", new Object[]{id}, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.queryForList("select * from authors", Author.class, new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
