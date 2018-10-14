package ru.otus.spring.bookinfo.shell;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.service.AuthorService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorCommandsTest {

    private static final int ID = 10;
    private static final String NAME = "TestName";

    @Mock
    private AuthorService serviceMock;

    private AuthorCommands commands;

    @Before
    public void setUp() {
        Author expectedAuthor = new Author();
        expectedAuthor.setId(ID);
        expectedAuthor.setName(NAME);
        List<Author> expectedAuthors = Collections.singletonList(expectedAuthor);
        commands = new AuthorCommands(serviceMock);
        when(serviceMock.getById(ID)).thenReturn(expectedAuthor);
        when(serviceMock.getAll()).thenReturn(expectedAuthors);
    }

    @Test
    public void countAuthors() {
        commands.countAuthors();
        Mockito.verify(serviceMock, times(1))
                .count();
    }

    @Test
    public void insertAuthor() {
        commands.insertAuthor(NAME);
        Mockito.verify(serviceMock, times(1))
                .insert(eq(NAME));
    }

    @Test
    public void deleteAuthor() {
        commands.deleteAuthor(ID);
        Mockito.verify(serviceMock, times(1))
                .delete(eq(ID));
    }

    @Test
    public void getAuthor() {
        commands.getAuthor(ID);
        Mockito.verify(serviceMock, times(1))
                .getById(eq(ID));
    }

    @Test
    public void listAuthors() {
        commands.listAuthors();
        Mockito.verify(serviceMock, times(1))
                .getAll();
    }
}