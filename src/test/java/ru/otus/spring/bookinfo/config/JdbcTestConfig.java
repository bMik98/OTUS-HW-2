package ru.otus.spring.bookinfo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcOperations;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.dao.jdbc.JdbcAuthorDao;
import ru.otus.spring.bookinfo.dao.jdbc.JdbcBookDao;
import ru.otus.spring.bookinfo.dao.jdbc.JdbcGenreDao;

@TestConfiguration
public class JdbcTestConfig {

    @Bean
    public GenreDao jdbcGenreDao(JdbcOperations jdbcOperations) {
        return new JdbcGenreDao(jdbcOperations);
    }

    @Bean
    public AuthorDao jdbcAuthorDao(JdbcOperations jdbcOperations) {
        return new JdbcAuthorDao(jdbcOperations);
    }

    @Bean
    public BookDao jdbcBookDao(JdbcOperations jdbcOperations, AuthorDao authorDao, GenreDao genreDao) {
        return new JdbcBookDao(jdbcOperations, authorDao, genreDao);
    }
}
