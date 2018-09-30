package ru.otus.spring.bookinfo.shell;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.service.BookService;

import java.util.Collections;
import java.util.List;

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
    private BookService serviceMock;

    private BookCommands commands;

    @Before
    public void setUp() {
        commands = new BookCommands(serviceMock);
        when(serviceMock.getById(ID))
                .thenReturn(expectedBook);
        when(serviceMock.getAll())
                .thenReturn(expectedBooks);
    }

    @Test
    public void countBooks() {
        commands.countBooks();
        Mockito.verify(serviceMock, times(1))
                .count();
    }

    @Test
    public void insertBook() {
        commands.insertBook(NAME);
        Mockito.verify(serviceMock, times(1))
                .insert(eq(NAME));
    }

    @Test
    public void deleteBook() {
        commands.deleteBook(ID);
        Mockito.verify(serviceMock, times(1))
                .delete(eq(ID));
    }

    @Test
    public void getBook() {
        commands.getBook(ID);
        Mockito.verify(serviceMock, times(1))
                .getById(eq(ID));
    }

    @Test
    public void listBooks() {
        commands.listBooks();
        Mockito.verify(serviceMock, times(1))
                .getAll();
    }
}