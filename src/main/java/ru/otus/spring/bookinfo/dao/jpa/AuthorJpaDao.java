package ru.otus.spring.bookinfo.dao.jpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Qualifier("authorDao")
@SuppressWarnings("JpaQlInspection")
public class AuthorJpaDao implements AuthorDao {

    private static final String COUNT_QUERY = "select count(e) from Author e";
    private static final String SELECT_ALL_QUERY = "select e from Author e";

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public int count() {
        TypedQuery<Long> query = em.createQuery(COUNT_QUERY, Long.class);
        return query.getSingleResult().intValue();
    }

    @Override
    public void save(Author p) {
        em.persist(p);
    }

    @Override
    public void delete(Author entity) {
        em.remove(entity);
    }

    @Override
    public Author getById(int id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery(SELECT_ALL_QUERY, Author.class);
        return query.getResultList();
    }
}
