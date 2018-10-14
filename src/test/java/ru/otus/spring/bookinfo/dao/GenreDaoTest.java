package ru.otus.spring.bookinfo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@SpringBootTest
@RunWith(SpringRunner.class)
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
        long before = genreDao.count();
        genreDao.save(createGenre(EXPECTED_NAME));
        assertEquals(before + 1, genreDao.count());
        int expectedId = (int) before + 1;
        Optional<Genre> optionalGenre = genreDao.findById(expectedId);
        assertTrue(optionalGenre.isPresent());
        Genre genre = optionalGenre.get();
        assertEquals(EXPECTED_NAME, genre.getName());
        genreDao.delete(genre);
        assertEquals(before, genreDao.count());
    }

    private Genre createGenre(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        return genre;
    }

    @Test
    public void getById() {
        for (Integer id = 1; id <= EXPECTED_COUNT; id++) {
            Optional<Genre> optionalGenre = genreDao.findById(id);
            assertTrue(optionalGenre.isPresent());
            Genre genre = optionalGenre.get();
            assertEquals(id, genre.getId());
        }
    }

    @Test
    public void getAll() {
        List<Genre> listBefore = genreDao.findAll();
        assertEquals(EXPECTED_COUNT, listBefore.size());
    }

    @Test
    public void getByWrongId() {
        Optional<Genre> genre = genreDao.findById(EXPECTED_COUNT + 100);
        assertFalse(genre.isPresent());
    }

    @Test
    public void update() {
        List<Genre> genres = genreDao.findAll();
        int count = genres.size();
        assertTrue(count > 0);
        Genre genre = genres.get(0);
        int id = genre.getId();
        String oldName = genre.getName();
        genre.setName(EXPECTED_NAME);
        assertNotEquals(EXPECTED_NAME, oldName);
        genreDao.save(genre);
        assertEquals("The number of genres has not been changed", count, genreDao.findAll().size());
        Optional<Genre> optionalUpdatedGenre = genreDao.findById(id);
        assertTrue(optionalUpdatedGenre.isPresent());
        assertEquals(EXPECTED_NAME, optionalUpdatedGenre.get().getName());
    }
}