package ru.otus.spring.bookinfo.domain;

import javax.persistence.Entity;

@Entity
public class Author extends AbstractEntity {

    public Author(int id, String name) {
        super(id, name);
    }
}
