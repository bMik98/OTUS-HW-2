package ru.otus.spring.bookinfo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.domain.Genre;

import java.util.List;

@Repository
public interface GenreDao extends CrudRepository<Genre, Integer> {

    List<Genre> findAll();

}
