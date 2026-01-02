package com.example.graphqldemo.service;

import com.example.graphqldemo.model.Category;
import com.example.graphqldemo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Optional<Category> getCategory(Long id) {
        return categoryRepo.findById(id);
    }

    public Category updateCategory(Long id, String name) {
        Category category = categoryRepo.findById(id).orElseThrow();
        category.setName(name);
        return categoryRepo.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
