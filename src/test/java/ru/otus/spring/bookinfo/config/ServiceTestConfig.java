package ru.otus.spring.bookinfo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.service.AuthorService;
import ru.otus.spring.bookinfo.service.BookService;
import ru.otus.spring.bookinfo.service.GenreService;

@TestConfiguration
public class ServiceTestConfig {

    @Bean
    public GenreService genreService(GenreDao genreDao) {
        return new GenreService(genreDao);
    }

    @Bean
    public AuthorService authorService(AuthorDao authorDao) {
        return new AuthorService(authorDao);
    }

    @Bean
    public BookService bookService(BookDao bookDao, GenreService genreService, AuthorService authorService) {
        return new BookService(bookDao, genreService, authorService);
    }
}
