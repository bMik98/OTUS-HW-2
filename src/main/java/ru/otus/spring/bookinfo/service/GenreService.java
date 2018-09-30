package ru.otus.spring.bookinfo.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Genre;

@Service
public class GenreService extends BasicEntityService<Genre> {

    public GenreService(GenreDao genreDao) {
        super(genreDao);
    }

    public void insert(String name) {
        save(new Genre(name));
    }
}

