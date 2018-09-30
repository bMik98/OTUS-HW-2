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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@DataJpaTest
@SpringBootTest
@RunWith(SpringRunner.class)
@Import(DaoTestConfig.class)
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
        int before = bookDao.count();
        bookDao.save(new Book(0, EXPECTED_NAME));
        assertEquals(before + 1, bookDao.count());
        int expectedId = before + 1;
        Book book = bookDao.getById(expectedId);
        assertEquals(EXPECTED_NAME, book.getName());
        bookDao.delete(book);
        assertEquals(before, bookDao.count());
    }

    @Test
    public void getById() {
        for (int id = 1; id <= EXPECTED_COUNT; id++) {
            Book book = bookDao.getById(id);
            assertEquals(id, book.getId());
        }
    }

    @Test
    public void getAll() {
        List<Book> list = bookDao.getAll();
        assertEquals(EXPECTED_COUNT, list.size());
    }

    @Test
    public void getByWrongId() {
        Book book = bookDao.getById(EXPECTED_COUNT + 100);
        assertNull(book);
    }

//    @Test
//    public void bindAndUnbindGenre() {
//        Book book = bookDao.getById(5);
//        int expectedNumberOfGenres = book.getGenres().size();
//        Genre genre = genreDao.getById(1);
//        bookDao.bind(book, genre);
//        checkGenres(book, expectedNumberOfGenres + 1);
//        bookDao.unbind(book, genre);
//        checkGenres(book, expectedNumberOfGenres);
//    }

    private void checkGenres(Book book, int expectedNumberOfGenres) {
        Book queriedBook = bookDao.getById(book.getId());
        assertEquals(expectedNumberOfGenres, queriedBook.getGenres().size());
    }

//    @Test
//    public void bindAndUnbindAuthor() {
//        Book book = bookDao.getById(4);
//        int expectedNumberOfAuthors = book.getAuthors().size();
//        Author author = authorDao.getById(3);
//        bookDao.bind(book, author);
//        checkAuthors(book, expectedNumberOfAuthors + 1);
//        bookDao.unbind(book, author);
//        checkAuthors(book, expectedNumberOfAuthors);
//    }

    private void checkAuthors(Book book, int expectedNumberOfAuthors) {
        Book queriedBook = bookDao.getById(book.getId());
        assertEquals(expectedNumberOfAuthors, queriedBook.getAuthors().size());
    }
}