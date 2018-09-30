package ru.otus.spring.bookinfo.shell;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.otus.spring.bookinfo.domain.BasicEntity;
import ru.otus.spring.bookinfo.domain.Book;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ShowUtils {

    static void showEntityCount(int count) {
        System.out.println(count);
    }

    static <E extends BasicEntity> void showEntity(E entity) {
        displayTitle();
        displaySingleEntity(entity);
    }

    static <E extends BasicEntity> void showEntityList(List<E> entities) {
        displayTitle();
        entities.forEach(ShowUtils::displaySingleEntity);
    }

    private static void displayTitle() {
        System.out.println("ID        Name");
        System.out.println("--------- --------------------------------------------------");
    }

    private static <E extends BasicEntity> void displaySingleEntity(E entity) {
        System.out.printf("%9d %-50s %n", entity.getId(), entity.getName());
    }

    static void showBook(Book entity) {
        displayBookTitle();
        displaySingleEntity(entity);
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
}
