package ru.otus.spring.bookinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    private Set<Genre> genre;

    @ManyToMany
    private Set<Author> author;

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

//    public void setGenres(Collection<Genre> genres) {
//        this.genre = new HashSet<>(genres);
//    }
//
//    public void setAuthors(Collection<Author> authors) {
//        this.author = new HashSet<>(authors);
//    }

    @Override
    public String toString() {
        return "" + id;
    }
}
