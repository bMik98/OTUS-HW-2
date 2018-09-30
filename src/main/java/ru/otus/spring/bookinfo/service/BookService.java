package ru.otus.spring.bookinfo.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

@Service
public class BookService extends BasicEntityService<Book> {

    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    public BookService(BookDao bookDao, GenreDao genreDao, AuthorDao authorDao) {
        super(bookDao);
        this.genreDao = genreDao;
        this.authorDao = authorDao;
    }

    public void insert(String name) {
        dao.save(new Book(name));
    }

    public void bindBookWithGenre(int bookId, int genreId) {
        Genre genre = genreDao.getById(genreId);
        Book book = dao.getById(bookId);
        book.addGenre(genre);
        dao.save(book);
        genreDao.save(genre);
    }

    public void bindBookWithAuthor(int bookId, int authorId) {
        Author author = authorDao.getById(authorId);
        Book book = dao.getById(bookId);
        book.addAuthor(author);
        dao.save(book);
        authorDao.save(author);
    }
}
