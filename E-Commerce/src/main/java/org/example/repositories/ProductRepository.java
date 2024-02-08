package org.example.repositories;

import org.example.model.Category;
import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    Optional<Product> findByCategory(String category);

//    Optional<List<Product>> findAllByCategory(String category);

    Optional<Product> findByName(String name);

    Optional<List<Product>> findAllByProductCategory(Category category);

//    Optional<List<Product>> findAllByCategory(Category category);

//    Optional<List<Product>> findAllByProductCategory(Category productCategory);

//    Product findByName(String name);

//    Product findByProduct(Product product);
}
