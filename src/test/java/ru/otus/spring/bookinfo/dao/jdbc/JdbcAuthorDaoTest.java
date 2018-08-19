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
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(JdbcTestConfig.class)
public class JdbcAuthorDaoTest {

    private static final int EXPECTED_COUNT = 3;
    private static final String EXPECTED_NAME = "TestName";
    private static final Book BOOK = new Book(1, "");
    private static final Book BOOK_3 = new Book(5, "");

    @Autowired
    private AuthorDao dao;

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
        dao.insert(new Author(0, EXPECTED_NAME));
        assertEquals(before + 1, dao.count());
        int expectedId = before + 1;
        Author author = dao.getById(expectedId);
        assertEquals(EXPECTED_NAME, author.getName());
        dao.delete(author);
        assertEquals(before, dao.count());
    }

    @Test
    public void getById() {
        for (int id = 1; id <= EXPECTED_COUNT; id++) {
            Author author = dao.getById(id);
            assertEquals(id, author.getId());
        }
    }

    @Test
    public void getAll() {
        List<Author> list = dao.getAll();
        assertEquals(EXPECTED_COUNT, list.size());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getByWrongId() {
        dao.getById(EXPECTED_COUNT + 100);
    }

    @Test
    public void getByBook() {
        List<Author> authors = dao.getByBook(BOOK);
        assertEquals(1, authors.size());
        List<Author> authors3 = dao.getByBook(BOOK_3);
        assertEquals(3, authors3.size());
    }
}