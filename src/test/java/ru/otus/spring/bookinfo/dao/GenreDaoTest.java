package ru.otus.spring.bookinfo.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.bookinfo.config.DaoTestConfig;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@SpringBootTest
@RunWith(SpringRunner.class)
@Import(DaoTestConfig.class)
public class GenreDaoTest {

    private static final int EXPECTED_COUNT = 5;
    private static final String EXPECTED_NAME = "TestName";

    @Autowired
    private GenreDao genreDao;

    @Test
    public void count() {
        assertEquals(EXPECTED_COUNT, genreDao.count());
    }

    @Test
    public void insertGetAndDelete() {
        int before = genreDao.count();
        genreDao.save(new Genre(EXPECTED_NAME));
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
        assertEquals(EXPECTED_COUNT, listBefore.size());
    }

    @Test
    public void getByWrongId() {
        Genre genre = genreDao.getById(EXPECTED_COUNT + 100);
        assertNull(genre);
    }

    @Test
    public void update() {
        List<Genre> genres = genreDao.getAll();
        int count = genres.size();
        assertTrue(count > 0);
        Genre genre = genres.get(0);
        int id = genre.getId();
        String oldName = genre.getName();
        genre.setName(EXPECTED_NAME);
        assertNotEquals(EXPECTED_NAME, oldName);
        genreDao.save(genre);
        assertEquals("The number of genres has not been changed", count, genreDao.getAll().size());
        Genre updatedGenre = genreDao.getById(id);
        assertEquals(EXPECTED_NAME, updatedGenre.getName());
    }
}