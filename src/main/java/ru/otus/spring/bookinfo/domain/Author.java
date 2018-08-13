package ru.otus.spring.bookinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Author {

    private final int id;

    private final String name;
}
