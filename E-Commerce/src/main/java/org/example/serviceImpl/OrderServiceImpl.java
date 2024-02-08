package org.example.serviceImpl;

import org.aspectj.weaver.ast.Or;
import org.example.dto.EmailDto;
import org.example.model.*;
import org.example.repositories.CartRepository;
import org.example.repositories.CustomerRepository;
import org.example.repositories.OrderRepository;
import org.example.repositories.ProductRepository;
import org.example.service.OrderService;
//import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public ResponseEntity<List<Orders>> getOrder() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Orders>> getCustomerOrders(EmailDto emailDto) {
        Optional<Customer> customerOptional =  customerRepository.findByEmail(emailDto.getEmail());
        if (customerOptional.isEmpty()){
            throw new IllegalStateException("User Not Found");
        }
        Optional<List<Orders>> ordersOptional = orderRepository.findAllByCustomer(customerOptional.get());
        if (ordersOptional.isEmpty()){
            throw new IllegalStateException("No Orders Found");
        }
        List<Orders> ordersList = ordersOptional.get();
        return ResponseEntity.ok(ordersList);
    }

    @Override
    public ResponseEntity<Orders> makeOrder(EmailDto emailDto) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(emailDto.getEmail());
        if (customerOptional.isEmpty()){
            throw new IllegalStateException("User Not found");
        }
        Customer customer = customerOptional.get();
        Cart cart = customer.getCart();
        if (cart.getProductsAddedToCart() == null){
            throw  new IllegalStateException("Your cart is empty");
        }
        //get productList from cart
        List<Product> productList = cart.getProductsAddedToCart();



        //set productList to null in cart
        cart.setProductsAddedToCart(null);
        //make order
//        Orders orders = new Orders();
//        orders.setTimeCreated(new Date(System.currentTimeMillis()));
//        orders.setOrderStatus(Status.PENDING);
//        orders.setProductsOrdered(productList);
//        orders.setTotalPrice(calculateTotalPrice(productList));
//        orders.setCustomer(customer);
        var orders = Orders.builder()
                .timeProcessed(new Date(System.currentTimeMillis()))
                        .orderStatus(Status.PENDING)
                                .productsOrdered(productList)
                                        .totalPrice(productList.stream().mapToDouble((p)->p.getPrice()).sum())
                                                .customer(customer)
                                                        .build();

        //set order in products

        customer.setCart(null);
        customer.getOrder().add(orders);
        orderRepository.save(orders);
        productRepository.saveAll(productList);
        cartRepository.delete(cart);
        customerRepository.save(customer);

        return ResponseEntity.ok(orders);
    }



}
