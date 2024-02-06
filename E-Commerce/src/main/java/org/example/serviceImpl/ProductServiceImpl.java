package org.example.serviceImpl;

import org.example.dto.CategoryDto;
import org.example.dto.ProductDto;
import org.example.model.Category;
import org.example.model.Product;
import org.example.repositories.ProductRepository;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Product>> getProductsByCategory(CategoryDto productCategory) {
        Optional<List<Product>> productOptional = productRepository.findAllByProductCategory(productCategory.getCategory());
        if (productOptional.isEmpty()){
            throw new IllegalStateException("Not found");
        }
        List<Product> productList = productOptional.get();
        return ResponseEntity.ok(productList);
    }

    @Override
    public ResponseEntity<Product> getProductsByName(String productName) {
        Optional<Product> productOptional = productRepository.findByName(productName);
        if (productOptional.isEmpty()){
            throw new IllegalStateException("Product not found");
        }
        Product product = productOptional.get();
        return ResponseEntity.ok(product);
    }
}
