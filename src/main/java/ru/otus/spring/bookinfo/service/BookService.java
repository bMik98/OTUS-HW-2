package ru.otus.spring.bookinfo.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.List;

@Service
public class BookService {

    private final BookDao bookDao;
    private final GenreService genreService;
    private final AuthorService authorService;

    public BookService(BookDao bookDao, GenreService genreService, AuthorService authorService) {
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    public void insert(String name) {
        Book book = new Book();
        book.setName(name);
        save(book);
    }

    private void save(Book book) {
        bookDao.save(book);
    }

    public long count() {
        return bookDao.count();
    }

    public void delete(int id) {
        bookDao.deleteById(id);
    }

    public Book getById(int id) {
        return bookDao.findById(id).orElse(null);
    }

    public List<Book> getAll() {
        return bookDao.findAll();
    }

    public void bindBookWithGenre(int bookId, int genreId) {
        Genre genre = genreService.getById(genreId);
        Book book = getById(bookId);
        if (genre != null && book != null) {
            book.addGenre(genre);
            save(book);
            genreService.save(genre);
        }
    }

    public void bindBookWithAuthor(int bookId, int authorId) {
        Author author = authorService.getById(authorId);
        Book book = getById(bookId);
        if (author != null && book != null) {
            book.addAuthor(author);
            save(book);
            authorService.save(author);
        }
    }
}
