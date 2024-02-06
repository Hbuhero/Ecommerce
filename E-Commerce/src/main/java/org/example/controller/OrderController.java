package org.example.controller;

import org.example.dto.EmailDto;
import org.example.model.Orders;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/all-orders")
    public ResponseEntity<List<Orders>> getOrder(){
        return orderService.getOrder();
    }

    @PostMapping("/customer-order")
    public ResponseEntity<List<Orders>> getCustomerOrders(
            @RequestBody EmailDto emailDto
    ){
        return orderService.getCustomerOrders(emailDto);
    }

    @PostMapping("/make-order")
    public ResponseEntity<Orders> makeOrder(
            @RequestBody EmailDto emailDto
    ){
        return orderService.makeOrder(emailDto);
    }

    //delete order if still pending
}
