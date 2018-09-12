package ru.otus.spring.bookinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "GENRES")
@Getter
@AllArgsConstructor
public class Genre implements BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final int id;

    private final String name;

    @Override
    public String toString() {
        return "" + id;
    }
}
