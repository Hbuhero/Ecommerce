package org.example.repositories;

import org.example.model.Customer;
import org.example.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    Optional<List<Orders>> findAllByCustomer(Customer customer);
}
