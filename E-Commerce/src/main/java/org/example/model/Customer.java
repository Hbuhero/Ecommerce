package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "First Name", nullable = false)
    private String firstname;

    @Column(name = "Second Name", nullable = false)
    private String lastname;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "customer")
//    @JsonIgnore
//    @JoinColumn(name = "orders_id", referencedColumnName = "id")
    private List<Orders> order;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Cart cart;

    public Customer(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
