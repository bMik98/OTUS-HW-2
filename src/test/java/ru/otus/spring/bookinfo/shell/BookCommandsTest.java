package ru.otus.spring.bookinfo.shell;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Book;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookCommandsTest {

    private static final int ID = 10;
    private static final String NAME = "TestName";
    private static final Book expectedBook = new Book(ID, NAME);
    private static final List<Book> expectedBooks = Collections.singletonList(expectedBook);

    @Mock
    private BookDao daoMock;
    @Mock
    private AuthorDao authorDaoMock;
    @Mock
    private GenreDao genreDaoMock;

    private BookCommands commands;

    @Before
    public void setUp() {
        commands = new BookCommands(daoMock, genreDaoMock, authorDaoMock);
        when(daoMock.getById(ID)).thenReturn(expectedBook);
        when(daoMock.getAll()).thenReturn(expectedBooks);
    }

    @Test
    public void countBooks() {
        commands.countBooks();
        Mockito.verify(daoMock, times(1))
                .count();
    }

    @Test
    public void insertBook() {
        commands.insertBook("any");
        Mockito.verify(daoMock, times(1))
                .insert(any(Book.class));
    }

    @Test
    public void deleteBook() {
        commands.deleteBook(ID);
        Mockito.verify(daoMock, times(1))
                .delete(any(Book.class));
    }

    @Test
    public void getBook() {
        commands.getBook(ID);
        Mockito.verify(daoMock, times(1))
                .getById(eq(ID));
    }

    @Test
    public void listBooks() {
        commands.listBooks();
        Mockito.verify(daoMock, times(1))
                .getAll();
    }
}