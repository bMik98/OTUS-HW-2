package ru.otus.spring.bookinfo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.bookinfo.domain.Book;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@DataJpaTest
@SpringBootTest
@RunWith(SpringRunner.class)
public class BookDaoTest {

    private static final int EXPECTED_COUNT = 6;
    private static final String EXPECTED_NAME = "TestName";

    @Autowired
    private BookDao bookDao;

    @Test
    public void count() {
        assertEquals(EXPECTED_COUNT, bookDao.count());
    }

    @Test
    public void insertGetAndDelete() {
        long before = bookDao.count();
        bookDao.save(createBook(EXPECTED_NAME));
        assertEquals(before + 1, bookDao.count());
        int expectedId = (int) before + 1;
        Optional<Book> optionalBook = bookDao.findById(expectedId);
        assertTrue(optionalBook.isPresent());
        Book book = optionalBook.get();
        assertEquals(EXPECTED_NAME, book.getName());
        bookDao.delete(book);
        assertEquals(before, bookDao.count());
    }

    private Book createBook(String name) {
        Book book = new Book();
        book.setName(name);
        return book;
    }

    @Test
    public void getById() {
        for (Integer id = 1; id <= EXPECTED_COUNT; id++) {
            Optional<Book> optionalBook = bookDao.findById(id);
            assertTrue(optionalBook.isPresent());
            assertEquals(id, optionalBook.get().getId());
        }
    }

    @Test
    public void getAll() {
        List<Book> list = bookDao.findAll();
        assertEquals(EXPECTED_COUNT, list.size());
    }

    @Test
    public void getByWrongId() {
        Optional<Book> optionalBook = bookDao.findById(EXPECTED_COUNT + 100);
        assertFalse(optionalBook.isPresent());
    }
}