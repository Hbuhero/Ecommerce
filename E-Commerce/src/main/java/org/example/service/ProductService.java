package org.example.service;

import org.example.dto.CategoryDto;
import org.example.dto.ProductDto;
import org.example.model.Category;
import org.example.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<List<Product>> getProducts();

//    ResponseEntity<List<Product>> getProductsByCategory(String category);

    ResponseEntity<Product> getProductsByName(String productName);

    ResponseEntity<List<Product>> getProductsByCategory(CategoryDto category);
}
