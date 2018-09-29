package ru.otus.spring.bookinfo.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class JpaGenreDao implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void insert(Genre p) {
        em.persist(p);
    }

    @Override
    public void delete(Genre entity) {
        TypedQuery<Genre> query = em.createQuery(
                "select e from Genre e where e.id = :id",
                Genre.class);
        query.setParameter("id", entity.getId());
    }

    @Override
    public Genre getById(int id) {
        return em.find(Genre.class, id);
    }

    public Genre getFirst() {
        TypedQuery<Genre> query = em.createQuery(
                "select e from Genre e where e.id = 1",
                Genre.class);
        return query.getSingleResult();
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select e from Genre e", Genre.class);
        return query.getResultList();
    }

    public Genre getByName(String name) {
        TypedQuery<Genre> query = em.createQuery(
                "select e from Genre e where e.name = :name",
                Genre.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Genre> getByBook(Book book) {
        return null;
    }
}
