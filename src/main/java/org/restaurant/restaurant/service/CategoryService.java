package org.restaurant.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurant.dtos.CategoryDTO;
import org.restaurant.restaurant.models.Category;
import org.restaurant.restaurant.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        Category categoryCur = getCategoryById(id);
        categoryCur.setName(categoryDTO.getName());
        categoryRepository.save(categoryCur);
        return categoryCur;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
