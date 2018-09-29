package ru.otus.spring.bookinfo.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
@ShellComponent
public class BookCommands extends AbstractCommands<Book> {

    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    @Autowired
    public BookCommands(
            @Qualifier("bookJpaDao") BookDao bookDao,
            @Qualifier("genreJpaDao") GenreDao genreDao,
            @Qualifier("authorJpaDao") AuthorDao authorDao) {
        super(bookDao);
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.authorDao = authorDao;
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

    @ShellMethod("Bind a genre with a book")
    public void bindGenre(int bookId, int genreId) {
        Book book = bookDao.getById(bookId);
        Genre genre = genreDao.getById(genreId);
        bookDao.bind(book, genre);
        getEntity(bookId);
    }

    @ShellMethod("Bind an author with a book")
    public void bindAuthor(int bookId, int authorId) {
        Book book = bookDao.getById(bookId);
        Author author = authorDao.getById(authorId);
        bookDao.bind(book, author);
        getEntity(bookId);
    }

    @ShellMethod("Unbind a genre from a book")
    public void unbindGenre(int bookId, int genreId) {
        Book book = bookDao.getById(bookId);
        Genre genre = genreDao.getById(genreId);
        bookDao.unbind(book, genre);
        getEntity(bookId);
    }

    @ShellMethod("Unbind an author from a book")
    public void unbindAuthor(int bookId, int authorId) {
        Book book = bookDao.getById(bookId);
        Author author = authorDao.getById(authorId);
        bookDao.unbind(book, author);
        getEntity(bookId);
    }

    @Override
    void showTitle() {
        System.out.println("ID        Name                                   Authors         Genres");
        System.out.println("--------- -------------------------------------- --------------- ---------------");
    }

    @Override
    void showEntity(Book book) {
        System.out.printf("%9d %-38s %-15s %-15s%n", book.getId(), book.getName(), book.getAuthor(), book.getGenre());
    }

    @Override
    protected Book createEntity(String name) {
        return new Book(0, name);
    }
}
