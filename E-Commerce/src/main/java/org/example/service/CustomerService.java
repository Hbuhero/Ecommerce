package org.example.service;

import org.example.dto.CustomerDto;
import org.example.dto.EmailChangeDto;
import org.example.dto.EmailDto;
import org.example.model.Customer;
import org.example.model.Orders;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    ResponseEntity<String> registerCustomer(CustomerDto customerDto);

    ResponseEntity<String> updateEmail(EmailChangeDto emailChangeDto);

//    ResponseEntity<List<Orders>> getCustomerOrders(String email);

    ResponseEntity<String> deleteCustomer(EmailDto emailDto);

    ResponseEntity<String> deleteCustomerCart(EmailDto emailDto);
}
