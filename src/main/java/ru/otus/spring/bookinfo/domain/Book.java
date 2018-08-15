package ru.otus.spring.bookinfo.domain;

import lombok.Getter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Book extends AbstractEntity {

    private final Set<Genre> genres = new HashSet<>();
    private final Set<Author> authors = new HashSet<>();

    public Book(int id, String name) {
        super(id, name);
    }

    public void setGenres(Collection<Genre> genres) {
        this.genres.clear();
        this.genres.addAll(genres);
    }

    public void setAuthors(Collection<Author> authors) {
        this.authors.clear();
        this.authors.addAll(authors);
    }
}
