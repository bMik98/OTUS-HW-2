package ru.otus.spring.bookinfo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.bookinfo.config.DaoTestConfig;
import ru.otus.spring.bookinfo.domain.Author;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@DataJpaTest
@SpringBootTest
@RunWith(SpringRunner.class)
@Import(DaoTestConfig.class)
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
        int before = authorDao.count();
        authorDao.save(new Author(EXPECTED_NAME));
        assertEquals(before + 1, authorDao.count());
        int expectedId = before + 1;
        Author author = authorDao.getById(expectedId);
        assertEquals(EXPECTED_NAME, author.getName());
        authorDao.delete(author);
        assertEquals(before, authorDao.count());
    }

    @Test
    public void getById() {
        for (int id = 1; id <= EXPECTED_COUNT; id++) {
            Author author = authorDao.getById(id);
            assertEquals(id, author.getId());
        }
    }

    @Test
    public void getAll() {
        List<Author> list = authorDao.getAll();
        assertEquals(EXPECTED_COUNT, list.size());
    }

    @Test
    public void getByWrongId() {
        Author author = authorDao.getById(EXPECTED_COUNT + 100);
        assertNull(author);
    }

}