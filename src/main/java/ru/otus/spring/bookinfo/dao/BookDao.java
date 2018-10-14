package ru.otus.spring.bookinfo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.domain.Book;

import java.util.List;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

    List<Book> findAll();

}
