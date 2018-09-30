package ru.otus.spring.bookinfo.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
public class GenreJpaDao implements GenreDao {

    private static final String COUNT_QUERY = "select count(e) from Genre e";
    private static final String SELECT_ALL_QUERY = "select e from Genre e";

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count() {
        TypedQuery<Long> query = em.createQuery(COUNT_QUERY, Long.class);
        return query.getSingleResult().intValue();
    }

    @Override
    public void save(Genre p) {
        em.persist(p);
    }

    @Override
    public void delete(Genre entity) {
        em.remove(entity);
    }

    @Override
    public Genre getById(int id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery(SELECT_ALL_QUERY, Genre.class);
        return query.getResultList();
    }
}
