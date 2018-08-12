package ru.otus.spring.bookinfo.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.service.AuthorService;

@Service
public class AuthorServiceImpl extends AbstractGenericService<Author> implements AuthorService {

    public AuthorServiceImpl(AuthorDao dao) {
        super(dao);
    }

    @Override
    protected Author createEntity(String name) {
        return new Author(0, name);
    }
}
