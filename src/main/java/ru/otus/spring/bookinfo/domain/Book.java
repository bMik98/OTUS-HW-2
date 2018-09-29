package ru.otus.spring.bookinfo.domain;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Book implements BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    private Collection<Book> genres = new ArrayList<>();

    @ManyToMany
    private Collection<Book> authors = new ArrayList<>();

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
