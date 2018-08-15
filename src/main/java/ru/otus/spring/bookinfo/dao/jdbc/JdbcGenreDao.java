package ru.otus.spring.bookinfo.dao.jdbc;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Genre;

@Repository
public class JdbcGenreDao extends AbstractJdbcEntityDao<Genre> implements GenreDao {

    public JdbcGenreDao(JdbcOperations jdbcOperations) {
        super(jdbcOperations);
    }

    @Override
    public String getEntityTableName() {
        return "genres";
    }

    @Override
    public RowMapper<Genre> createEntityMapper() {
        return (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return new Genre(id, name);
        };
    }
}
