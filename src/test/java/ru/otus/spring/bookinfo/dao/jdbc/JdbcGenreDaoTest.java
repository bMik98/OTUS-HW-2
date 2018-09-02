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
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(JdbcTestConfig.class)
public class JdbcGenreDaoTest {

    private static final int EXPECTED_COUNT = 5;
    private static final String EXPECTED_NAME = "TestName";
    private static final Book BOOK = new Book(4, "");
    private static final Book BOOK_2 = new Book(5, "");

    @Autowired
    private GenreDao dao;

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
        dao.insert(new Genre(0, EXPECTED_NAME));
        assertEquals(before + 1, dao.count());
        int expectedId = before + 1;
        Genre genre = dao.getById(expectedId);
        assertEquals(EXPECTED_NAME, genre.getName());
        dao.delete(genre);
        assertEquals(before, dao.count());
    }

    @Test
    public void getById() {
        for (int id = 1; id <= EXPECTED_COUNT; id++) {
            Genre genre = dao.getById(id);
            assertEquals(id, genre.getId());
        }
    }

    @Test
    public void getAll() {
        List<Genre> list = dao.getAll();
        assertEquals(EXPECTED_COUNT, list.size());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getByWrongId() {
        dao.getById(EXPECTED_COUNT + 100);
    }

    @Test
    public void getByBook() {
        List<Genre> genres = dao.getByBook(BOOK);
        assertEquals(1, genres.size());
        List<Genre> genres2 = dao.getByBook(BOOK_2);
        assertEquals(2, genres2.size());
    }
}