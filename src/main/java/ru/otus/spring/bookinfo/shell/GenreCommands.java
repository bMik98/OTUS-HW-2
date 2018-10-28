package ru.otus.spring.bookinfo.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.bookinfo.domain.Genre;
import ru.otus.spring.bookinfo.service.GenreService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
@ShellComponent
public class GenreCommands {

    private final GenreService service;

    public GenreCommands(GenreService genreService) {
        this.service = genreService;
    }

    @ShellMethod("Display the number of genres")
    public void countGenres() {
        ShowUtils.showCount(service.count());
    }

    @ShellMethod("Insert new genre")
    public void insertGenre(@ShellOption @NotEmpty String name) {
        service.insert(name);
        listGenres();
    }

    @ShellMethod("Delete a genre by ID")
    public void deleteGenre(@ShellOption @Positive int id) {
        service.delete(id);
        listGenres();
    }

    @ShellMethod("Find a genre by ID")
    public void getGenre(@ShellOption @Positive int id) {
        Genre genre = service.getById(id);
        if (genre == null) {
            ShowUtils.showError("Unable to find genre by id: " + id);
        } else {
            ShowUtils.showGenre(genre);
        }
    }

    @ShellMethod("Display all the genres")
    public void listGenres() {
        List<Genre> entries = service.getAll();
        ShowUtils.showGenreList(entries);
    }
}
