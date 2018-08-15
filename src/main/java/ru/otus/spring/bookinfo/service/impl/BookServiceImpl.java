package ru.otus.spring.bookinfo.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.service.BookService;

@Service
public class BookServiceImpl extends AbstractGenericService<Book> implements BookService {

    public BookServiceImpl(BookDao dao) {
        super(dao);
    }

    @Override
    protected Book createEntity(String name) {
        return new Book(0, name);
    }
}
