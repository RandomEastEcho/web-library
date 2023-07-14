package library.dao.impl;

import library.dao.AbstractDao;
import library.dao.CategoryDao;
import library.model.Category;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {
    public CategoryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Category.class);
    }
}
