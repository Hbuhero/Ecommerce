package org.example.controller;

import org.example.dto.CartRequestDto;
import org.example.dto.EmailChangeDto;
import org.example.dto.EmailDto;
import org.example.model.Cart;
import org.example.model.Product;
import org.example.service.CartService;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerService customerService;


    @PostMapping("/customer-cart")
    public ResponseEntity<Cart> getCart(
            @RequestBody EmailDto email
            ){
        return cartService.getCart(email);
    }

    // Spring does not take multiple Request Bodies so encapsulate the bodies into 1 class object
    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addProductToCart(
            @RequestBody CartRequestDto cartRequestDto
            ){
        return cartService.addProductToCart(cartRequestDto);
    }


    @PostMapping("/remove-from-cart")
    public ResponseEntity<String> removeProductFromCart(
            @RequestBody CartRequestDto cartRequestDto
    ){
        return cartService.removeProductFromCart(cartRequestDto);
    }

    @PostMapping("/delete-cart")
    public ResponseEntity<String> deleteCart(
            @RequestBody EmailDto emailDto
    ){
        return customerService.deleteCustomerCart(emailDto);
    }
}
