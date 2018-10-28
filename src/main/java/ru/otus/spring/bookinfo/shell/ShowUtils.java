package ru.otus.spring.bookinfo.shell;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ShowUtils {

    static void showCount(long count) {
        System.out.println(count);
    }

    static void showAuthorList(List<Author> authors) {
        displayTitle();
        authors.forEach(author -> displaySimpleEntity(author.getId(), author.getName()));
    }

    static void showAuthor(Author author) {
        displayTitle();
        displaySimpleEntity(author.getId(), author.getName());
    }

    static void showGenreList(List<Genre> genres) {
        displayTitle();
        genres.forEach(genre -> displaySimpleEntity(genre.getId(), genre.getName()));
    }

    static void showGenre(Genre genre) {
        displayTitle();
        displaySimpleEntity(genre.getId(), genre.getName());
    }

    private static void displayTitle() {
        System.out.println("ID        Name");
        System.out.println("--------- --------------------------------------------------");
    }

    private static void displaySimpleEntity(int id, String name) {
        System.out.printf("%9d %-50s %n", id, name);
    }

    static void showBook(Book entity) {
        displayBookTitle();
        displayBook(entity);
    }

    static void showBookList(List<Book> entities) {
        displayBookTitle();
        entities.forEach(ShowUtils::displayBook);
    }

    private static void displayBookTitle() {
        System.out.println("ID        Name                                   Authors         Genres");
        System.out.println("--------- -------------------------------------- --------------- ---------------");
    }

    private static void displayBook(Book book) {
        System.out.printf("%9d %-38s %-15s %-15s%n",
                book.getId(), book.getName(), book.getAuthors(), book.getGenres());
    }

    static void showError(String message) {
        System.out.println("*** Error *** " + message);
    }
}
