package ru.otus.spring.bookinfo.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Genre;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
@ShellComponent
public class GenreCommands extends AbstractCommands<Genre> {

    @Autowired
    public GenreCommands(GenreDao genreDao) {
        super(genreDao);
    }

    @ShellMethod("Display the number of genres")
    public void countGenres() {
        showCount();
    }

    @ShellMethod("Insert new Genre")
    public void insertGenre(@ShellOption @NotEmpty String name) {
        insertEntity(name);
    }

    @ShellMethod("Delete Genre by ID")
    public void deleteGenre(@ShellOption @Positive int id) {
        deleteEntity(id);
    }

    @ShellMethod("Find Genre by ID")
    public void getGenre(@ShellOption @Positive int id) {
        getEntity(id);
    }

    @ShellMethod("Display all the genres")
    public void listGenres() {
        listEntities();
    }

    @Override
    protected Genre createEntity(String name) {
        return new Genre(0, name);
    }
}
