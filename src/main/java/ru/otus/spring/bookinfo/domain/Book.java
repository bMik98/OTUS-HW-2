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
public class Book implements BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    private Collection<Genre> genres = new ArrayList<>();

    @ManyToMany
    private Collection<Author> authors = new ArrayList<>();

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
