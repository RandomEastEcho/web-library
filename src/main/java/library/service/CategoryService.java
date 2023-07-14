package library.service;

import java.util.List;
import library.model.Category;

public interface CategoryService {
    Category add(Category category);

    Category get(Long id);

    List<Category> getAll();

    Category update(Category category);

    void delete(Long id);
}
