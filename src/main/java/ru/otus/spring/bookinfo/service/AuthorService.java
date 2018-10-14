package ru.otus.spring.bookinfo.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.domain.Author;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorDao authorDao;

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public void insert(String name) {
        Author author = new Author();
        author.setName(name);
        save(author);
    }

    void save(Author author) {
        authorDao.save(author);
    }

    public long count() {
        return authorDao.count();
    }

    public void delete(int id) {
        authorDao.deleteById(id);
    }

    public Author getById(int id) {
        return authorDao.findById(id).orElse(null);
    }

    public List<Author> getAll() {
        return authorDao.findAll();
    }
}
