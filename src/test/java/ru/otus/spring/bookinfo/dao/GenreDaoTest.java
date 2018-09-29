package ru.otus.spring.bookinfo.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.bookinfo.config.DaoTestConfig;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(DaoTestConfig.class)
public class GenreDaoTest {

    private static final int EXPECTED_COUNT = 5;
    private static final String EXPECTED_NAME = "TestName";
    private static final Book BOOK = new Book(4, "");
    private static final Book BOOK_2 = new Book(5, "");

    @Autowired
    private GenreDao genreDao;

    @Before
    public void setUp() {
    }

    @Test
    public void count() {
        assertEquals(EXPECTED_COUNT, genreDao.count());
    }

    @Test
    public void insertGetAndDelete() {
        int before = genreDao.count();
        genreDao.insert(new Genre(0, EXPECTED_NAME));
        assertEquals(before + 1, genreDao.count());
        int expectedId = before + 1;
        Genre genre = genreDao.getById(expectedId);
        assertEquals(EXPECTED_NAME, genre.getName());
        genreDao.delete(genre);
        assertEquals(before, genreDao.count());
    }

    @Test
    public void getById() {
        for (int id = 1; id <= EXPECTED_COUNT; id++) {
            Genre genre = genreDao.getById(id);
            assertNotNull(genre);
            assertEquals(id, genre.getId());
        }
    }

    @Test
    public void getAll() {
        List<Genre> listBefore = genreDao.getAll();
        assertEquals(0, listBefore.size());
        genreDao.insert(new Genre(0, "test"));
        assertEquals(1, genreDao.getAll().size());
        genreDao.insert(new Genre(0, "test2"));
        assertEquals(2, genreDao.getAll().size());
    }

    @Test
    public void getByWrongId() {
        Genre genre = genreDao.getById(EXPECTED_COUNT + 100);
        assertNull(genre);
    }

    @Test
    public void getByBook() {
        List<Genre> genres = genreDao.getByBook(BOOK);
        assertEquals(1, genres.size());
        List<Genre> genres2 = genreDao.getByBook(BOOK_2);
        assertEquals(2, genres2.size());
    }
}