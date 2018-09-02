package ru.otus.spring.bookinfo.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.bookinfo.config.JdbcTestConfig;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(JdbcTestConfig.class)
public class JdbcBookDaoTest {

    private static final int EXPECTED_COUNT = 6;
    private static final String EXPECTED_NAME = "TestName";

    @Autowired
    private BookDao dao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private AuthorDao authorDao;

    @Before
    public void setUp() {
    }

    @Test
    public void count() {
        assertEquals(EXPECTED_COUNT, dao.count());
    }

    @Test
    public void insertGetAndDelete() {
        int before = dao.count();
        dao.insert(new Book(0, EXPECTED_NAME));
        assertEquals(before + 1, dao.count());
        int expectedId = before + 1;
        Book book = dao.getById(expectedId);
        assertEquals(EXPECTED_NAME, book.getName());
        dao.delete(book);
        assertEquals(before, dao.count());
    }

    @Test
    public void getById() {
        for (int id = 1; id <= EXPECTED_COUNT; id++) {
            Book book = dao.getById(id);
            assertEquals(id, book.getId());
        }
    }

    @Test
    public void getAll() {
        List<Book> list = dao.getAll();
        assertEquals(EXPECTED_COUNT, list.size());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getByWrongId() {
        dao.getById(EXPECTED_COUNT + 100);
    }

    @Test
    public void bindAndUnbindGenre() {
        Book book = dao.getById(5);
        int expectedNumberOfGenres = book.getGenres().size();
        Genre genre = genreDao.getById(1);
        dao.bind(book, genre);
        checkGenres(book, expectedNumberOfGenres + 1);
        dao.unbind(book, genre);
        checkGenres(book, expectedNumberOfGenres);
    }

    private void checkGenres(Book book, int expectedNumberOfGenres) {
        Book queriedBook = dao.getById(book.getId());
        assertEquals(expectedNumberOfGenres, queriedBook.getGenres().size());
    }

    @Test
    public void bindAndUnbindAuthor() {
        Book book = dao.getById(4);
        int expectedNumberOfAuthors = book.getAuthors().size();
        Author author = authorDao.getById(3);
        dao.bind(book, author);
        checkAuthors(book, expectedNumberOfAuthors + 1);
        dao.unbind(book, author);
        checkAuthors(book, expectedNumberOfAuthors);
    }

    private void checkAuthors(Book book, int expectedNumberOfAuthors) {
        Book queriedBook = dao.getById(book.getId());
        assertEquals(expectedNumberOfAuthors, queriedBook.getAuthors().size());
    }
}