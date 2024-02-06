package org.example.serviceImpl;

import org.example.dto.CartRequestDto;
import org.example.dto.EmailChangeDto;
import org.example.dto.EmailDto;
import org.example.dto.ProductDTO;
import org.example.model.Cart;
import org.example.model.Customer;
import org.example.model.Product;
import org.example.repositories.CartRepository;
import org.example.repositories.CustomerRepository;
import org.example.repositories.ProductRepository;
import org.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<Cart> getCart(EmailDto email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email.getEmail());
        if (customerOptional.isEmpty()){
            throw new IllegalStateException("Not Found");
        }
        Customer customer = customerOptional.get();

        return ResponseEntity.ok(customer.getCart());
    }
    // instantiating and initializing of objects is necessary
    //when dealing with one object do not include its name in json body.
    //just list the fields. Example cartRequestDto when put in json body is different
    //from emaildto

    @Override
    public ResponseEntity<String> addProductToCart(CartRequestDto cartRequestDto) {

        Product product= getProductDTOFromRequest.apply(cartRequestDto);

        //finding customer
        Customer customer = getCustomerFromRequest.apply(cartRequestDto);

        //adding the product to the cart
        if (customer.getCart() == null){
           Cart cart = new Cart();
           List<Product> productList = new ArrayList<>();

           productList.add(product);
           cart.setProductsAddedToCart(productList);
           cart.setCustomer(customer);
           customer.setCart(cart);
           cartRepository.save(cart);
        }else {
            customer.getCart().getProductsAddedToCart().add(product);

        }

        customerRepository.save(customer);
        return ResponseEntity.ok("Added to Cart");
    }
    // when something is null it is not initialized so be able to handle null point exception
    // in bi-directional relationship, always set the related fields like product.setCart and cart.setProducts

    @Override
    public ResponseEntity<String> removeProductFromCart(CartRequestDto cartRequestDto) {

        Product product = getProductDTOFromRequest.apply(cartRequestDto);
        Cart cart = getCart(cartRequestDto.getEmail()).getBody();
        if (cart.getProductsAddedToCart().isEmpty()){
            throw new IllegalStateException("Cart is empty");
        }
        cart.getProductsAddedToCart().remove(product);
        return ResponseEntity.ok("Removed from Cart");
    }

     Function<CartRequestDto, Product> getProductDTOFromRequest = new Function<CartRequestDto, Product>() {
        @Override
        public Product apply(CartRequestDto cartRequestDto) {
            //finding product from repo
            Optional<Product> productOptional = productRepository.findById(cartRequestDto.getProductDTO().getId());
            if (productOptional.isEmpty()){
                throw new IllegalStateException("Product not found");
            }

            //building productDTO from product
            return productOptional.get();

        }
    };

    Function<CartRequestDto, Customer> getCustomerFromRequest = new Function<CartRequestDto, Customer>() {
        @Override
        public Customer apply(CartRequestDto cartRequestDto) {
            Optional<Customer> customerOptional = customerRepository.findByEmail(cartRequestDto.getEmail().getEmail());
            if (customerOptional.isEmpty()){
                throw new IllegalStateException("Not Found");
            }

            return customerOptional.get();
        }
    };
}
