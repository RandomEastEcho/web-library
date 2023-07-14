package library.service.impl;

import java.util.List;
import library.dao.CategoryDao;
import library.model.Category;
import library.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category add(Category category) {
        return categoryDao.add(category);
    }

    @Override
    public Category get(Long id) {
        return categoryDao.get(id).orElseThrow(
                () -> new RuntimeException("Can`t find category with id: " + id));
    }

    @Override
    public Category update(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public void delete(Long id) {
        categoryDao.delete(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }
}
