package ru.otus.spring.bookinfo.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.service.BookService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
@ShellComponent
public class BookCommands {

    private final BookService service;

    public BookCommands(BookService bookService) {
        this.service = bookService;
    }

    @ShellMethod("Display the number of Books")
    public void countBooks() {
        ShowUtils.showEntityCount(service.count());
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
        Book book = service.getById(id);
        ShowUtils.showBook(book);
    }

    @ShellMethod("Display all the Books")
    public void listBooks() {
        List<Book> entries = service.getAll();
        ShowUtils.showBookList(entries);
    }

//    @ShellMethod("Bind a genre with a book")
//    public void bindGenre(int bookId, int genreId) {
//        Book book = bookDao.getById(bookId);
//        Genre genre = genreDao.getById(genreId);
//        bookDao.bind(book, genre);
//        getEntity(bookId);
//    }
//
//    @ShellMethod("Bind an author with a book")
//    public void bindAuthor(int bookId, int authorId) {
//        Book book = bookDao.getById(bookId);
//        Author author = authorDao.getById(authorId);
//        bookDao.bind(book, author);
//        getEntity(bookId);
//    }
//
//    @ShellMethod("Unbind a genre from a book")
//    public void unbindGenre(int bookId, int genreId) {
//        Book book = bookDao.getById(bookId);
//        Genre genre = genreDao.getById(genreId);
//        bookDao.unbind(book, genre);
//        getEntity(bookId);
//    }
//
//    @ShellMethod("Unbind an author from a book")
//    public void unbindAuthor(int bookId, int authorId) {
//        Book book = bookDao.getById(bookId);
//        Author author = authorDao.getById(authorId);
//        bookDao.unbind(book, author);
//        getEntity(bookId);
//    }


}
