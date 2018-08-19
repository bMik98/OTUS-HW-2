package ru.otus.spring.bookinfo.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractEntityTest {

    private static final Integer ID = 1;

    @Test
    public void checkToString() {
        AbstractEntity abstractEntity = new AbstractEntity(1, "test") {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        assertEquals(ID.toString(), abstractEntity.toString());
    }
}