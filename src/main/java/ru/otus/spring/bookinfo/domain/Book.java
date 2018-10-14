package ru.otus.spring.bookinfo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany
    private Collection<Genre> genres = new ArrayList<>();

    @ManyToMany
    private Collection<Author> authors = new ArrayList<>();

    public void addGenre(Genre genre) {
        if (!getGenres().contains(genre)) {
            getGenres().add(genre);
        }
        if (!genre.getBooks().contains(this)) {
            genre.getBooks().add(this);
        }
    }

    public void addAuthor(Author author) {
        if (!getAuthors().contains(author)) {
            getAuthors().add(author);
        }
        if (!author.getBooks().contains(this)) {
            author.getBooks().add(this);
        }
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
