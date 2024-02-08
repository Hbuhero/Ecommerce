package org.example.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.ProductDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double totalPrice;

    @Enumerated(value = EnumType.STRING)
    private Status orderStatus;

    private Date timeCreated;

    private Date timeProcessed;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"order", "id"})
    private Customer customer;


    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "orders", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "products", referencedColumnName = "id")
    )
    private List<Product> productsOrdered;





}
