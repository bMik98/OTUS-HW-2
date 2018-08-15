package ru.otus.spring.bookinfo.dao.jdbc;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.List;

@Repository
public class JdbcGenreDao extends AbstractJdbcEntityDao<Genre> implements GenreDao {

    private static final String RELATED_TABLE = "book_genres";
    private static final String FK = "genre_id";

    public JdbcGenreDao(JdbcOperations jdbcOperations) {
        super(jdbcOperations);
    }

    @Override
    public List<Genre> getByBook(Book book) {
        String subSql = "(select " + FK + " from " + RELATED_TABLE + " where book_id = " + book.getId() + ")";
        String sql = "select * from " + getEntityTableName() + " where id in " + subSql;
        return jdbc.query(sql, createEntityMapper());
    }

    @Override
    public void delete(Genre genre) {
        super.delete(genre);
        jdbc.update("delete from " + RELATED_TABLE + " where " + FK + " = ?", genre.getId());
    }

    @Override
    public String getEntityTableName() {
        return "genres";
    }

    @Override
    protected RowMapper<Genre> createEntityMapper() {
        return (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return new Genre(id, name);
        };
    }
}
