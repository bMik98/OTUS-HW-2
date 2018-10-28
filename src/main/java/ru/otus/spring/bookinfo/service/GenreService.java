package ru.otus.spring.bookinfo.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.List;

@Service
public class GenreService {

    private final GenreDao genreDao;

    public GenreService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public void insert(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        save(genre);
    }

    void save(Genre genre) {
        genreDao.save(genre);
    }

    public long count() {
        return genreDao.count();
    }

    public void delete(int id) {
        genreDao.deleteById(id);
    }

    public Genre getById(int id) {
        return genreDao.findById(id).orElse(null);
    }

    public List<Genre> getAll() {
        return genreDao.findAll();
    }
}

