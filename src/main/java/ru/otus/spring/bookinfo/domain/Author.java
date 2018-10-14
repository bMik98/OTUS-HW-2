package ru.otus.spring.bookinfo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "authors")
    private Collection<Book> books = new ArrayList<>();

    @Override
    public String toString() {
        return "" + id;
    }
}
