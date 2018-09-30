package ru.otus.spring.bookinfo.dao.jpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Qualifier("bookDao")
@SuppressWarnings("JpaQlInspection")
public class BookJpaDao implements BookDao {

    private static final String COUNT_QUERY = "select count(e) from Book e";
    private static final String SELECT_ALL_QUERY = "select e from Book e";

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public int count() {
        TypedQuery<Long> query = em.createQuery(COUNT_QUERY, Long.class);
        return query.getSingleResult().intValue();
    }

    @Override
    public void save(Book p) {
        em.persist(p);
    }

    @Override
    public void delete(Book entity) {
        em.remove(entity);
    }

    @Override
    public Book getById(int id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery(SELECT_ALL_QUERY, Book.class);
        return query.getResultList();
    }
}
