package ru.otus.spring.bookinfo.domain;

import javax.persistence.Entity;

@Entity
public class Genre extends AbstractEntity {

    public Genre(int id, String name) {
        super(id, name);
    }
}
