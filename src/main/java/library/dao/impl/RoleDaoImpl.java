package library.dao.impl;

import java.util.Optional;
import library.dao.AbstractDao;
import library.dao.RoleDao;
import library.exception.DataProcessingException;
import library.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Role.class);
    }

    @Override
    public Optional<Role> getByName(Role.RoleName roleName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Role WHERE roleName = :roleName", Role.class)
                    .setParameter("roleName", roleName)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get role by name: " + roleName, e);
        }
    }
}
