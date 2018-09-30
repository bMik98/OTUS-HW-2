package ru.otus.spring.bookinfo.shell;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.domain.Author;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorCommandsTest {

    private static final int ID = 10;
    private static final String NAME = "TestName";
    private static final Author expectedAuthor = new Author(ID, NAME);
    private static final List<Author> expectedAuthors = Collections.singletonList(expectedAuthor);

    @Mock
    private AuthorDao daoMock;

    private AuthorCommands commands;

    @Before
    public void setUp() {
        commands = new AuthorCommands(daoMock);
        when(daoMock.getById(ID)).thenReturn(expectedAuthor);
        when(daoMock.getAll()).thenReturn(expectedAuthors);
    }

    @Test
    public void countAuthors() {
        commands.countAuthors();
        Mockito.verify(daoMock, times(1))
                .count();
    }

    @Test
    public void insertAuthor() {
        commands.insertAuthor("any");
        Mockito.verify(daoMock, times(1))
                .save(any(Author.class));
    }

    @Test
    public void deleteAuthor() {
        commands.deleteAuthor(ID);
        Mockito.verify(daoMock, times(1))
                .delete(any(Author.class));
    }

    @Test
    public void getAuthor() {
        commands.getAuthor(ID);
        Mockito.verify(daoMock, times(1))
                .getById(eq(ID));
    }

    @Test
    public void listAuthors() {
        commands.listAuthors();
        Mockito.verify(daoMock, times(1))
                .getAll();
    }
}