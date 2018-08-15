package ru.otus.spring.bookinfo.dao.jdbc;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;

import java.util.List;

@Repository
public class JdbcAuthorDao extends AbstractJdbcEntityDao<Author> implements AuthorDao {

    private static final String RELATED_TABLE = "book_authors";
    private static final String FK = "author_id";

    public JdbcAuthorDao(JdbcOperations jdbcOperations) {
        super(jdbcOperations);
    }

    @Override
    public List<Author> getByBook(Book book) {
        String subSql = "(select " + FK + " from " + RELATED_TABLE + " where book_id = " + book.getId() + ")";
        String sql = "select * from " + getEntityTableName() + " where id in " + subSql;
        return jdbc.query(sql, createEntityMapper());
    }

    @Override
    public void delete(Author author) {
        super.delete(author);
        jdbc.update("delete from " + RELATED_TABLE + " where " + FK + " = ?", author.getId());
    }

    @Override
    public String getEntityTableName() {
        return "authors";
    }

    @Override
    protected RowMapper<Author> createEntityMapper() {
        return (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return new Author(id, name);
        };
    }
}
