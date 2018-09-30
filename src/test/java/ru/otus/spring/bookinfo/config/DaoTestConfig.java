package ru.otus.spring.bookinfo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.dao.jpa.AuthorJpaDao;
import ru.otus.spring.bookinfo.dao.jpa.BookJpaDao;
import ru.otus.spring.bookinfo.dao.jpa.GenreJpaDao;

@TestConfiguration
public class DaoTestConfig {

    @Bean
    public GenreDao genreDao() {
        return new GenreJpaDao();
    }

    @Bean
    public AuthorDao authorDao() {
        return new AuthorJpaDao();
    }

    @Bean
    public BookDao bookDao() {
        return new BookJpaDao();
    }
}
