package library.dao;

import java.util.List;
import java.util.Optional;
import library.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    protected final SessionFactory sessionFactory;
    private final Class<T> clazz;

    public AbstractDao(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    public T add(T t) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t add "
                        + clazz.getSimpleName() + " " + t, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Optional<T> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(clazz, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find "
            + clazz.getSimpleName() + ",by id: " + id, e);
        }
    }

    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get all "
                    + clazz.getSimpleName() + "s from db", e);
        }
    }

    public T update(T t) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t update "
                + clazz.getSimpleName() + " " + t, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            T book = session.get(clazz, id);
            session.delete(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t delete "
                + clazz.getSimpleName() + " with id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
