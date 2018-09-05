package ru.otus.spring.bookinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
public class Genre implements BasicEntity {

    @Id
    @GeneratedValue
    private final int id;

    private final String name;

    @Override
    public String toString() {
        return "" + id;
    }
}
