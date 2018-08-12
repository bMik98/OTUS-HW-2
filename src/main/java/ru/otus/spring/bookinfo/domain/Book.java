package ru.otus.spring.bookinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class Book {

    private final long id;

    private final String name;

    Set<Genre> genres;

    Set<Author> authors;
}
