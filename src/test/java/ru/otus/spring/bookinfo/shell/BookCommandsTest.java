package ru.otus.spring.bookinfo.shell;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.service.BookService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookCommandsTest {

    private static final int ID = 10;
    private static final String NAME = "TestName";
    
    @Mock
    private BookService serviceMock;

    private BookCommands commands;

    @Before
    public void setUp() {
        Book expectedBook = new Book();
        expectedBook.setId(ID);
        expectedBook.setName(NAME);
        List<Book> expectedBookList = Collections.singletonList(expectedBook);
        commands = new BookCommands(serviceMock);
        when(serviceMock.getById(ID))
                .thenReturn(expectedBook);
        when(serviceMock.getAll())
                .thenReturn(expectedBookList);
    }

    @Test
    public void countBooks() {
        commands.countBooks();
        verify(serviceMock, times(1))
                .count();
    }

    @Test
    public void insertBook() {
        commands.insertBook(NAME);
        verify(serviceMock, times(1))
                .insert(eq(NAME));
    }

    @Test
    public void deleteBook() {
        commands.deleteBook(ID);
        verify(serviceMock, times(1))
                .delete(eq(ID));
    }

    @Test
    public void getBook() {
        commands.getBook(ID);
        verify(serviceMock, times(1))
                .getById(eq(ID));
    }

    @Test
    public void listBooks() {
        commands.listBooks();
        verify(serviceMock, times(1))
                .getAll();
    }

    @Test
    public void bindGenre() {
        int genreId = 25;
        commands.bindGenre(ID, genreId);
        verify(serviceMock, times(1))
                .bindBookWithGenre(eq(ID), eq(genreId));
    }

    @Test
    public void bindAuthor() {
        int authorId = 15;
        commands.bindAuthor(ID, authorId);
        verify(serviceMock, times(1))
                .bindBookWithAuthor(eq(ID), eq(authorId));
    }
}