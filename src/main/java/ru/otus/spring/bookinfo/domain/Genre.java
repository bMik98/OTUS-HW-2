package ru.otus.spring.bookinfo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "genres")
    private Collection<Book> books = new ArrayList<>();

    @Override
    public String toString() {
        return "" + id;
    }
}
