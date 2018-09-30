package ru.otus.spring.bookinfo.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.domain.Author;

@Service
public class AuthorService extends BasicEntityService<Author> {

    public AuthorService(AuthorDao authorDao) {
        super(authorDao);
    }

    public void insert(String name) {
        dao.save(new Author(name));
    }

}
