package ru.otus.spring.bookinfo.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.service.BookService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
@ShellComponent
public class BookCommands {
    private final BookService service;

    @Autowired
    public BookCommands(BookService service) {
        this.service = service;
    }

    @ShellMethod("Display the number of Books")
    public void countBooks() {
        System.out.println(service.count());
    }

    @ShellMethod("Insert new Book")
    public void insertBook(@ShellOption @NotEmpty String name) {
        service.insert(name);
        listBooks();
    }

    @ShellMethod("Delete Book by ID")
    public void deleteBook(@ShellOption @Positive int id) {
        service.delete(id);
        listBooks();
    }

    @ShellMethod("Find Book by ID")
    public void getBook(@ShellOption @Positive int id) {
        showTitle();
        showBook(service.getById(id));
    }

    @ShellMethod("Display all the Books")
    public void listBooks() {
        showTitle();
        service.getAll().forEach(this::showBook);
    }

    private void showTitle() {
        System.out.println("ID         Book");
        System.out.println("---------- ------------------------------------------------");
    }

    private void showBook(Book book) {
        System.out.printf("%10d %s %n", book.getId(), book.getName());
    }
}
