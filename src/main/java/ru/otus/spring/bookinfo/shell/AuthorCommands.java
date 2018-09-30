package ru.otus.spring.bookinfo.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.service.AuthorService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
@ShellComponent
public class AuthorCommands {

    private final AuthorService service;

    public AuthorCommands(AuthorService authorService) {
        this.service = authorService;
    }

    @ShellMethod("Display the number of authors")
    public void countAuthors() {
        ShowUtils.showEntityCount(service.count());
    }

    @ShellMethod("Insert new author")
    public void insertAuthor(@ShellOption @NotEmpty String name) {
        service.insert(name);
        listAuthors();
    }

    @ShellMethod("Delete an author by ID")
    public void deleteAuthor(@ShellOption @Positive int id) {
        service.delete(id);
        listAuthors();
    }

    @ShellMethod("Find an author by ID")
    public void getAuthor(@ShellOption @Positive int id) {
        Author entity = service.getById(id);
        ShowUtils.showEntity(entity);
    }

    @ShellMethod("Display all the authors")
    public void listAuthors() {
        List<Author> entries = service.getAll();
        ShowUtils.showEntityList(entries);
    }
}
