package ru.otus.spring.bookinfo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Genre implements BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "genres")
    private Collection<Book> books = new ArrayList<>();

    public Genre(String name) {
        this.name = name;
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

//    public void addBook(Book book) {
//        if (!getBooks().contains(book)) {
//            getBooks().add(book);
//        }
//        if (!book.getGenres().contains(this)) {
//            book.getGenres().add(this);
//        }
//    }

    @Override
    public String toString() {
        return "" + id;
    }
}
