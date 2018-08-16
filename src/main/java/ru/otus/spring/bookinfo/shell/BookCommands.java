package ru.otus.spring.bookinfo.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.domain.Book;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
@ShellComponent
public class BookCommands extends AbstractCommands<Book> {

    @Autowired
    public BookCommands(BookDao bookDao) {
        super(bookDao);
    }

    @ShellMethod("Display the number of Books")
    public void countBooks() {
        showCount();
    }

    @ShellMethod("Insert new Book")
    public void insertBook(@ShellOption @NotEmpty String name) {
        insertEntity(name);
    }

    @ShellMethod("Delete Book by ID")
    public void deleteBook(@ShellOption @Positive int id) {
        deleteEntity(id);
    }

    @ShellMethod("Find Book by ID")
    public void getBook(@ShellOption @Positive int id) {
        getEntity(id);
    }

    @ShellMethod("Display all the Books")
    public void listBooks() {
        listEntities();
    }

    @Override
    protected Book createEntity(String name) {
        return new Book(0, name);
    }
}
