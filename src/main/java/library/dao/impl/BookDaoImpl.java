package library.dao.impl;

import java.util.List;
import java.util.Optional;
import library.dao.AbstractDao;
import library.dao.BookDao;
import library.exception.DataProcessingException;
import library.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl extends AbstractDao<Book> implements BookDao {
    public BookDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Book.class);
    }

    @Override
    public List<Book> getAllBooks() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Book b JOIN FETCH b.category name", Book.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get all books", e);
        }
    }

    @Override
    public Optional<Book> getBook(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Book> getBookById = session.createQuery(
                    "FROM Book  b JOIN FETCH b.category WHERE b.id = :id", Book.class);
            getBookById.setParameter("id", id);
            return getBookById.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find book by id: " + id, e);
        }
    }
}
