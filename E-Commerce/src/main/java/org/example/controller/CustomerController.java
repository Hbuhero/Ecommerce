package org.example.controller;

import org.example.dto.CustomerDto;
import org.example.dto.EmailChangeDto;
import org.example.dto.EmailDto;
import org.example.model.Customer;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @GetMapping("/customer-all")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping("/register-customer")
    public ResponseEntity<String> registerCustomer(
            @RequestBody CustomerDto customerDto
    ){
        return customerService.registerCustomer(customerDto);
    }

    @PostMapping("/delete-customer")
    public ResponseEntity<String> deleteCustomer(
            @RequestBody EmailDto emailDto
            ){
        return customerService.deleteCustomer(emailDto);
    }

    @PostMapping("/update-customer-email")
    public ResponseEntity<String> updateEmail(
            @RequestBody EmailChangeDto emailChangeDto
    ){
        return customerService.updateEmail(emailChangeDto);
    }

//    @PostMapping("/customer-order")
//    public ResponseEntity<List<Orders>> getCustomerOrders(
//            @RequestBody String email
//    ){
//        return customerService.getCustomerOrders(email);
//    }



}
