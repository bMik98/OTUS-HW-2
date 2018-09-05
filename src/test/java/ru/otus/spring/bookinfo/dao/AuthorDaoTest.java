package ru.otus.spring.bookinfo.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.bookinfo.config.DaoTestConfig;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(DaoTestConfig.class)
public class AuthorDaoTest {

    private static final int EXPECTED_COUNT = 3;
    private static final String EXPECTED_NAME = "TestName";
    private static final Book BOOK = new Book(1, "");
    private static final Book BOOK_3 = new Book(5, "");

    @Autowired
    private AuthorDao authorDao;

    @Before
    public void setUp() {
    }

    @Test
    public void count() {
        assertEquals(EXPECTED_COUNT, authorDao.count());
    }

    @Test
    public void insertGetAndDelete() {
        int before = authorDao.count();
        authorDao.insert(new Author(0, EXPECTED_NAME));
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

    @Test(expected = EmptyResultDataAccessException.class)
    public void getByWrongId() {
        authorDao.getById(EXPECTED_COUNT + 100);
    }

    @Test
    public void getByBook() {
        List<Author> authors = authorDao.getByBook(BOOK);
        assertEquals(1, authors.size());
        List<Author> authors3 = authorDao.getByBook(BOOK_3);
        assertEquals(3, authors3.size());
    }
}