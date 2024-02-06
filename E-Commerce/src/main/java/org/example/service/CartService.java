package org.example.service;

import org.example.dto.CartRequestDto;
import org.example.dto.EmailChangeDto;
import org.example.dto.EmailDto;
import org.example.model.Cart;
import org.example.model.Product;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<Cart> getCart(EmailDto email);

    ResponseEntity<String> addProductToCart(CartRequestDto cartRequestDto);

    ResponseEntity<String> removeProductFromCart(CartRequestDto cartRequestDto);
}
