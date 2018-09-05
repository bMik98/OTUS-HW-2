package ru.otus.spring.bookinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
public class Book implements BasicEntity {

    @Id
    @GeneratedValue
    private final int id;

    private final String name;

    @ManyToMany
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    private Set<Author> authors = new HashSet<>();

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setGenres(Collection<Genre> genres) {
        this.genres = new HashSet<>(genres);
    }

    public void setAuthors(Collection<Author> authors) {
        this.authors = new HashSet<>(authors);
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
