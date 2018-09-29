package ru.otus.spring.bookinfo.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class AuthorJpaDao implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void insert(Author p) {
        em.persist(p);
    }

    @Override
    public void delete(Author entity) {

    }

    @Override
    public Author getById(int id) {
        return em.find(Author.class, id);
    }

    public Author getFirst() {
        TypedQuery<Author> query = em.createQuery(
                "select e from Author e where e.id = 1",
                Author.class);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery(
                "select e from Author e",
                Author.class);
        return query.getResultList();
    }

    public Author getByName(String name) {
        TypedQuery<Author> query = em.createQuery(
                "select e from Author e where e.name = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getByBook(Book book) {
        return null;
    }
}
