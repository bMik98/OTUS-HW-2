package ru.otus.spring.bookinfo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.bookinfo.config.ServiceTestConfig;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@DataJpaTest
@SpringBootTest
@RunWith(SpringRunner.class)
@Import(ServiceTestConfig.class)
public class BookServiceTest {

    private static final int BOOK_ID = 1;
    private static final int GENRE_ID = 2;
    private static final int AUTHOR_ID = 3;

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private AuthorService authorService;

    @Test
    public void insert() {
        long before = bookService.count();
        String title = "NewTitle";
        bookService.insert(title);
        assertEquals(before + 1, bookService.count());
    }

    @Test
    public void bindBookWithGenre() {
        Book book = bookService.getById(BOOK_ID);
        Genre genre = genreService.getById(GENRE_ID);
        Collection<Genre> bookGenres = book.getGenres();
        int before = book.getGenres().size();
        assertFalse(bookGenres.contains(genre));
        bookService.bindBookWithGenre(book.getId(), genre.getId());
        Book updatedBook = bookService.getById(BOOK_ID);
        assertEquals(before + 1, updatedBook.getGenres().size());
    }

    @Test
    public void bindBookWithAuthor() {
        Book book = bookService.getById(BOOK_ID);
        Author author = authorService.getById(AUTHOR_ID);
        Collection<Author> bookAuthors = book.getAuthors();
        int before = book.getAuthors().size();
        assertFalse(bookAuthors.contains(author));
        bookService.bindBookWithAuthor(book.getId(), author.getId());
        Book updatedBook = bookService.getById(BOOK_ID);
        assertEquals(before + 1, updatedBook.getAuthors().size());
    }
}