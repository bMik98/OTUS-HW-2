package ru.otus.spring.bookinfo.shell;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.otus.spring.bookinfo.domain.Genre;
import ru.otus.spring.bookinfo.service.GenreService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GenreCommandsTest {

    private static final int ID = 10;
    private static final String NAME = "TestName";

    @Mock
    private GenreService serviceMock;

    private GenreCommands commands;

    @Before
    public void setUp() {
        Genre expectedGenre = new Genre();
        expectedGenre.setId(ID);
        expectedGenre.setName(NAME);
        List<Genre> expectedGenres = Collections.singletonList(expectedGenre);
        commands = new GenreCommands(serviceMock);
        when(serviceMock.getById(ID)).thenReturn(expectedGenre);
        when(serviceMock.getAll()).thenReturn(expectedGenres);
    }

    @Test
    public void countGenres() {
        commands.countGenres();
        Mockito.verify(serviceMock, times(1))
                .count();
    }

    @Test
    public void insertGenre() {
        commands.insertGenre(NAME);
        Mockito.verify(serviceMock, times(1))
                .insert(eq(NAME));
    }

    @Test
    public void deleteGenre() {
        commands.deleteGenre(ID);
        Mockito.verify(serviceMock, times(1))
                .delete(eq(ID));
    }

    @Test
    public void getGenre() {
        commands.getGenre(ID);
        Mockito.verify(serviceMock, times(1))
                .getById(eq(ID));
    }

    @Test
    public void listGenres() {
        commands.listGenres();
        Mockito.verify(serviceMock, times(1))
                .getAll();
    }
}