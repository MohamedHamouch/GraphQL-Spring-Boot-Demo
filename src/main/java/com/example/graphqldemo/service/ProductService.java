package com.example.graphqldemo.service;

import com.example.graphqldemo.model.Category;
import com.example.graphqldemo.model.Product;
import com.example.graphqldemo.repository.CategoryRepository;
import com.example.graphqldemo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public ProductService(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public Product createProduct(Long categoryId, String name, Double price) {
        Category category = categoryRepo.findById(categoryId).orElseThrow();
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);

        Product savedProduct = productRepo.save(product);

        if (category.getProducts() != null) {
            category.getProducts().add(savedProduct);
        }

        return savedProduct;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepo.findById(id);
    }

    public Product updateProduct(Long id, String name, Double price) {
        Product product = productRepo.findById(id).orElseThrow();
        if (name != null) product.setName(name);
        if (price != null) product.setPrice(price);
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
