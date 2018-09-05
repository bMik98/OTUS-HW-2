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
import ru.otus.spring.bookinfo.dao.jpa.JpaAuthorDao;
import ru.otus.spring.bookinfo.dao.jpa.JpaBookDao;
import ru.otus.spring.bookinfo.dao.jpa.JpaGenreDao;

@TestConfiguration
public class DaoTestConfig {

    @Bean
    public GenreDao genreDao() {
        return new JpaGenreDao();
    }

    @Bean
    public AuthorDao authorDao() {
        return new JpaAuthorDao();
    }

    @Bean
    public BookDao bookDao(AuthorDao authorDao, GenreDao genreDao) {
        return new JpaBookDao(authorDao, genreDao);
    }
}
