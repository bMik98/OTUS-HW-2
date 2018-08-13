package ru.otus.spring.bookinfo.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.service.AuthorService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
@ShellComponent
public class AuthorCommands {
    private final AuthorService service;

    @Autowired
    public AuthorCommands(AuthorService service) {
        this.service = service;
    }

    @ShellMethod("Display the number of authors")
    public void countAuthors() {
        System.out.println(service.count());
    }

    @ShellMethod("Insert new Author")
    public void insertAuthor(@ShellOption @NotEmpty String name) {
        service.insert(name);
        listAuthors();
    }

    @ShellMethod("Delete Author by ID")
    public void deleteAuthor(@ShellOption @Positive int id) {
        service.delete(id);
        listAuthors();
    }

    @ShellMethod("Find Author by ID")
    public void getAuthor(@ShellOption @Positive int id) {
        showTitle();
        showAuthor(service.getById(id));
    }

    @ShellMethod("Display all the authors")
    public void listAuthors() {
        showTitle();
        service.getAll().forEach(this::showAuthor);
    }

    private void showTitle() {
        System.out.println("ID         Author");
        System.out.println("---------- ------------------------------------------------");
    }

    private void showAuthor(Author author) {
        System.out.printf("%10d %s %n", author.getId(), author.getName());
    }
}
