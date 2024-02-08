package org.example.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.dto.CustomerDto;
import org.example.dto.EmailChangeDto;
import org.example.dto.EmailDto;
import org.example.model.Customer;
import org.example.model.Orders;
import org.example.repositories.CustomerRepository;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@Builder
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public ResponseEntity<String> registerCustomer(CustomerDto customerDto) {
        var customer = Customer.builder()
                .firstname(customerDto.getFirstname())
                .lastname(customerDto.getLastname())
                .email(customerDto.getEmail())
                .build();
        customerRepository.save(customer);
        return ResponseEntity.ok("Registration Complete");
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateEmail(EmailChangeDto emailChangeDto) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(emailChangeDto.getCurrentEmail());
        if (customerOptional.isEmpty()){
            return ResponseEntity.ok("Not found");
        }
        Customer customer = customerOptional.get();
        customer.setEmail(emailChangeDto.getNewEmail());
        customerRepository.save(customer);
        return ResponseEntity.ok("Successful");

        // Concept note, SRS, System design document, Technical Document
    }

//    @Override
//    public ResponseEntity<List<Orders>> getCustomerOrders(String email) {
//        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
//        if (customerOptional.isEmpty()){
//            throw new IllegalStateException("Not found");
//        }
//        Customer customer = customerOptional.get();
//        List<Orders> orders = customer.getOrder();
//        return ResponseEntity.ok(orders);
//    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteCustomerCart(EmailDto emailDto) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(emailDto.getEmail());
        Customer customer = customerOptional.get();
        customer.setCart(null);
        customerRepository.save(customer);
        return ResponseEntity.ok("Deleted cart");
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteCustomer(EmailDto emailDto) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(emailDto.getEmail());
        if (customerOptional.isEmpty()){
            throw new IllegalStateException("Not found");
        }
        Customer customer = customerOptional.get();
        customerRepository.delete(customer);
        return ResponseEntity.ok("Successful");
    }
}
