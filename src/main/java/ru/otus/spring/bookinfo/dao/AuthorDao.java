package ru.otus.spring.bookinfo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.domain.Author;

import java.util.List;

@Repository
public interface AuthorDao extends CrudRepository<Author, Integer> {

    List<Author> findAll();

}
