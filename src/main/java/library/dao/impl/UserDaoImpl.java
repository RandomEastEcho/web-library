package library.dao.impl;

import java.util.Optional;
import library.dao.AbstractDao;
import library.dao.UserDao;
import library.exception.DataProcessingException;
import library.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> findByEmail = session.createQuery(
                    "FROM User u JOIN FETCH u.roles WHERE u.email = :email", User.class);
            findByEmail.setParameter("email", email);
            return findByEmail.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find user by email: " + email, e);
        }
    }
}
