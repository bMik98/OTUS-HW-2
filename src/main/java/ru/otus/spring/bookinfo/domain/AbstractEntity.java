package ru.otus.spring.bookinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private final int id;
    private final String name;

    @Override
    public String toString() {
        return "" + id;
    }
}
