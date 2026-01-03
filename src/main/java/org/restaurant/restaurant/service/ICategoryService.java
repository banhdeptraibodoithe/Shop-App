package org.restaurant.restaurant.service;

import org.restaurant.restaurant.dtos.CategoryDTO;
import org.restaurant.restaurant.models.Category;

import java.util.List;

public interface ICategoryService {
    void createCategory(CategoryDTO category);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    Category updateCategory(Long id, CategoryDTO category);
    void deleteCategory(Long id);
}
