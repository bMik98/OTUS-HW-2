package ru.otus.spring.bookinfo.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Genre;
import ru.otus.spring.bookinfo.service.GenreService;

@Service
public class GenreServiceImpl extends AbstractGenericService<Genre> implements GenreService {

    public GenreServiceImpl(GenreDao dao) {
        super(dao);
    }

    @Override
    protected Genre createEntity(String name) {
        return new Genre(0, name);
    }
}
