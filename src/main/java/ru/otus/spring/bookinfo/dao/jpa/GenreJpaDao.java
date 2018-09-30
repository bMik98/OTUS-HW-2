package ru.otus.spring.bookinfo.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class GenreJpaDao implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count() {
        TypedQuery<Long> query = em.createQuery(
                "select count(e) from Genre e",
                Long.class);
        return query.getSingleResult().intValue();
    }

    @Override
    public void save(Genre p) {
        em.persist(p);
    }

    @Override
    public void delete(Genre entity) {
        Query query = em.createQuery("delete from Genre e where e.id = :id");
        query.setParameter("id", entity.getId());
        query.executeUpdate();
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
