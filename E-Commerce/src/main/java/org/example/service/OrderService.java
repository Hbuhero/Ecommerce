package org.example.service;

import org.example.dto.EmailDto;
import org.example.model.Orders;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<List<Orders>> getOrder();

    ResponseEntity<List<Orders>> getCustomerOrders(EmailDto emailDto);

    ResponseEntity<Orders> makeOrder(EmailDto emailDto);
}
