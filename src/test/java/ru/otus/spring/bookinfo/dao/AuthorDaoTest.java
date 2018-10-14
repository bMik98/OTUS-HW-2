package ru.otus.spring.bookinfo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.bookinfo.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorDaoTest {

    private static final int EXPECTED_COUNT = 3;
    private static final String EXPECTED_NAME = "TestName";

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void count() {
        assertEquals(EXPECTED_COUNT, authorDao.count());
    }

    @Test
    public void insertGetAndDelete() {
        long before = authorDao.count();
        authorDao.save(createAuthor(EXPECTED_NAME));
        assertEquals(before + 1, authorDao.count());
        int expectedId = (int) before + 1;
        Optional<Author> optionalAuthor = authorDao.findById(expectedId);
        assertTrue(optionalAuthor.isPresent());
        Author author = optionalAuthor.get();
        assertEquals(EXPECTED_NAME, author.getName());
        authorDao.delete(author);
        assertEquals(before, authorDao.count());
    }

    private Author createAuthor(String name) {
        Author author = new Author();
        author.setName(name);
        return author;
    }

    @Test
    public void getById() {
        for (Integer id = 1; id <= EXPECTED_COUNT; id++) {
            Optional<Author> optionalAuthor = authorDao.findById(id);
            assertTrue(optionalAuthor.isPresent());
            assertEquals(id, optionalAuthor.get().getId());
        }
    }

    @Test
    public void getAll() {
        List<Author> list = authorDao.findAll();
        assertEquals(EXPECTED_COUNT, list.size());
    }

    @Test
    public void getByWrongId() {
        Optional<Author> optionalAuthor = authorDao.findById(EXPECTED_COUNT + 100);
        assertFalse(optionalAuthor.isPresent());
    }
}