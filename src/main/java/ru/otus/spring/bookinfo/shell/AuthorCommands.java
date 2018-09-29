package ru.otus.spring.bookinfo.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.domain.Author;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
@ShellComponent
public class AuthorCommands extends AbstractCommands<Author> {

    @Autowired
    public AuthorCommands(@Qualifier("authorJpaDao") AuthorDao authorDao) {
        super(authorDao);
    }

    @ShellMethod("Display the number of authors")
    public void countAuthors() {
        showCount();
    }

    @ShellMethod("Insert new author")
    public void insertAuthor(@ShellOption @NotEmpty String name) {
        insertEntity(name);
    }

    @ShellMethod("Delete an author by ID")
    public void deleteAuthor(@ShellOption @Positive int id) {
        deleteEntity(id);
    }

    @ShellMethod("Find an author by ID")
    public void getAuthor(@ShellOption @Positive int id) {
        getEntity(id);
    }

    @ShellMethod("Display all the authors")
    public void listAuthors() {
        listEntities();
    }

    @Override
    protected Author createEntity(String name) {
        return new Author(0, name);
    }
}
