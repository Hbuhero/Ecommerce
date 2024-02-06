package org.example.controller;

import org.example.dto.CategoryDto;
import org.example.dto.ProductDto;
import org.example.model.Category;
import org.example.model.Product;
import org.example.repositories.ProductRepository;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts(){
        return productService.getProducts();
    }

    @PostMapping("/product-by-category")
    public ResponseEntity<List<Product>> getProductByCategory(
            @RequestBody CategoryDto category
            ){
        return productService.getProductsByCategory(category);
    }

    @PostMapping("/product-by-name")
    public ResponseEntity<Product> getProductByName(
            @RequestBody String productName
            ){
        return productService.getProductsByName(productName);
    }


}
