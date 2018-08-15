package ru.otus.spring.bookinfo.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.bookinfo.domain.Genre;
import ru.otus.spring.bookinfo.service.GenreService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
@ShellComponent
public class GenreCommands {
    private final GenreService service;

    @Autowired
    public GenreCommands(GenreService service) {
        this.service = service;
    }

    @ShellMethod("Display the number of genres")
    public void countGenres() {
        System.out.println(service.count());
    }

    @ShellMethod("Insert new Genre")
    public void insertGenre(@ShellOption @NotEmpty String name) {
        service.insert(name);
        listGenres();
    }

    @ShellMethod("Delete Genre by ID")
    public void deleteGenre(@ShellOption @Positive int id) {
        service.delete(id);
        listGenres();
    }

    @ShellMethod("Find Genre by ID")
    public void getGenre(@ShellOption @Positive int id) {
        showTitle();
        showGenre(service.getById(id));
    }

    @ShellMethod("Display all the genres")
    public void listGenres() {
        showTitle();
        service.getAll().forEach(this::showGenre);
    }

    private void showTitle() {
        System.out.println("ID         Genre");
        System.out.println("---------- ------------------------------------------------");
    }

    private void showGenre(Genre genre) {
        System.out.printf("%10d %s %n", genre.getId(), genre.getName());
    }
}
