package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Enumerated(value = EnumType.STRING)
    private Category productCategory;

    @Column(nullable = false)
    private Integer stock;

    public Product(String name, Double price, Category productCategory, Integer stock) {

        this.name = name;
        this.price = price;
        this.productCategory = productCategory;
        this.stock = stock;
    }
}
