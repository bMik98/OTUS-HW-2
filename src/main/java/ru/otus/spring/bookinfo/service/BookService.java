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

    private final GenreService genreService;
    private final AuthorService authorService;

    public BookService(BookDao bookDao, GenreService genreService, AuthorService authorService) {
        super(bookDao);
        this.genreService = genreService;
        this.authorService = authorService;
    }

    public void insert(String name) {
        save(new Book(name));
    }

    public void bindBookWithGenre(int bookId, int genreId) {
        Genre genre = genreService.getById(genreId);
        Book book = getById(bookId);
        book.addGenre(genre);
        save(book);
        genreService.save(genre);
    }

    public void bindBookWithAuthor(int bookId, int authorId) {
        Author author = authorService.getById(authorId);
        Book book = getById(bookId);
        book.addAuthor(author);
        save(book);
        authorService.save(author);
    }
}
