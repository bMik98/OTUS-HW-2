package ru.otus.spring.bookinfo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.bookinfo.config.ServiceTestConfig;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@SpringBootTest
@RunWith(SpringRunner.class)
@Import(ServiceTestConfig.class)
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void insert() {
        long before = authorService.count();
        String title = "NewTitle";
        authorService.insert(title);
        assertEquals(before + 1, authorService.count());
    }
}