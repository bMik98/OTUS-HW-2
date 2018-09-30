package ru.otus.spring.bookinfo.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Book;

@Service
public class BookService extends BasicEntityService<Book> {

    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    public BookService(BookDao bookDao, GenreDao genreDao, AuthorDao authorDao) {
        super(bookDao);
        this.genreDao = genreDao;
        this.authorDao = authorDao;
    }


}
