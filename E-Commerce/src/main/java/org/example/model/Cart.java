package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.example.dto.ProductDTO;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "products", referencedColumnName = "id")
    )
    private List<Product> productsAddedToCart;

    @OneToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"order", "firstname", "lastname"})
    private Customer customer;



}
