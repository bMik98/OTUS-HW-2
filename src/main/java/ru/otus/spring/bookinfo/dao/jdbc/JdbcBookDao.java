package ru.otus.spring.bookinfo.dao.jdbc;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookinfo.dao.AuthorDao;
import ru.otus.spring.bookinfo.dao.BookDao;
import ru.otus.spring.bookinfo.dao.GenreDao;
import ru.otus.spring.bookinfo.domain.Author;
import ru.otus.spring.bookinfo.domain.Book;
import ru.otus.spring.bookinfo.domain.Genre;

@Repository
public class JdbcBookDao extends AbstractJdbcEntityDao<Book> implements BookDao {

    private static final String BOOK_AUTHORS = "book_authors";
    private static final String BOOK_GENRES = "book_genres";
    private static final String AUTHOR_FK = "author_id";
    private static final String GENRE_FK = "genre_id";
    private static final String FK = "book_id";

    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public JdbcBookDao(JdbcOperations jdbcOperations, AuthorDao authorDao, GenreDao genreDao) {
        super(jdbcOperations);
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public void unbound(Book book, Author author) {
        String sql = "delete from " + BOOK_AUTHORS + " where " + FK + " = ? and " + AUTHOR_FK + " = ?";
        jdbc.update(sql, book.getId(), author.getId());
    }

    @Override
    public void unbound(Book book, Genre genre) {
        String sql = "delete from " + BOOK_GENRES + " where " + FK + " = ? and " + GENRE_FK + " = ?";
        jdbc.update(sql, book.getId(), genre.getId());
    }

    @Override
    public void bound(Book book, Author author) {
        String sql = "insert into " + BOOK_AUTHORS + " (" + FK + "," + AUTHOR_FK + ") values (?, ?)";
        jdbc.update(sql, book.getId(), author.getId());
    }

    @Override
    public void bound(Book book, Genre genre) {
        String sql = "insert into " + BOOK_GENRES + " (" + FK + "," + GENRE_FK + ") values (?, ?)";
        jdbc.update(sql, book.getId(), genre.getId());
    }


    @Override
    public String getEntityTableName() {
        return "books";
    }

    @Override
    protected RowMapper<Book> createEntityMapper() {
        return (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Book book = new Book(id, name);
            book.setAuthors(authorDao.getByBook(book));
            book.setGenres(genreDao.getByBook(book));
            return book;
        };
    }
}
