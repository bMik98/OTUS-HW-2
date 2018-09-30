package ru.otus.spring.bookinfo.shell;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GenreCommandsTest {

    private static final int ID = 10;
    private static final String NAME = "TestName";
    private static final Genre expectedGenre = new Genre(ID, NAME);
    private static final List<Genre> expectedGenres = Collections.singletonList(expectedGenre);

    @Mock
    private GenreDao daoMock;

    private GenreCommands commands;

    @Before
    public void setUp() {
        commands = new GenreCommands(daoMock);
        when(daoMock.getById(ID)).thenReturn(expectedGenre);
        when(daoMock.getAll()).thenReturn(expectedGenres);
    }

    @Test
    public void countGenres() {
        commands.countGenres();
        Mockito.verify(daoMock, times(1))
                .count();
    }

    @Test
    public void insertGenre() {
        commands.insertGenre("any");
        Mockito.verify(daoMock, times(1))
                .save(any(Genre.class));
    }

    @Test
    public void deleteGenre() {
        commands.deleteGenre(ID);
        Mockito.verify(daoMock, times(1))
                .delete(any(Genre.class));
    }

    @Test
    public void getGenre() {
        commands.getGenre(ID);
        Mockito.verify(daoMock, times(1))
                .getById(eq(ID));
    }

    @Test
    public void listGenres() {
        commands.listGenres();
        Mockito.verify(daoMock, times(1))
                .getAll();
    }
}