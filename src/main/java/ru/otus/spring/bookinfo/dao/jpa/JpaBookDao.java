package ru.otus.spring.bookinfo.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class JpaBookDao implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void insert(Book p) {
        em.persist(p);
    }

    @Override
    public void delete(Book entity) {

    }

    @Override
    public Book getById(int id) {
        return em.find(Book.class, id);
    }

    public Book getFirst() {
        TypedQuery<Book> query = em.createQuery(
                "select e from Book e where e.id = 1",
                Book.class);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery(
                "select e from Book e",
                Book.class);
        return query.getResultList();
    }

    public Book getByName(String name) {
        TypedQuery<Book> query = em.createQuery(
                "select e from Book e where e.name = :name",
                Book.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public void unbind(Book book, Author author) {

    }

    @Override
    public void unbind(Book book, Genre genre) {

    }

    @Override
    public void bind(Book book, Author author) {

    }

    @Override
    public void bind(Book book, Genre genre) {

    }
}
