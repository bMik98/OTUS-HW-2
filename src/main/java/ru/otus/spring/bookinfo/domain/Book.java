package ru.otus.spring.bookinfo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Book extends AbstractEntity {

    private Set<Genre> genres;
    private Set<Author> authors;

    public Book(int id, String name, Collection<Genre> genres, Collection<Author> authors) {
        super(id, name);
        this.genres = new HashSet<>(genres);
        this.authors = new HashSet<>(authors);
    }

    public Book(int id, String name) {
        this(id, name, new HashSet<>(), new HashSet<>());
    }
}
