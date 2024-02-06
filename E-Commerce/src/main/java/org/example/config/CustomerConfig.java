package org.example.config;

import org.example.model.Category;
import org.example.model.Product;
import org.example.repositories.CustomerRepository;
import org.example.model.Customer;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            Product product1 = new Product("Honda", 749.85, Category.ELECTRONICS, 56);
            Product product2 = new Product("Nissan", 796.99, Category.ELECTRONICS, 43);
            Product product3 = new Product("Toyota", 666.9, Category.ELECTRONICS, 21);
            Product product4 = new Product("Table Cloth", 183.08, Category.CLOTHING, 11);
            Product product5 = new Product("Mace", 641.96, Category.CLOTHING, 1);
            Product product6 = new Product("Beer", 7.85, Category.FOOD, 0);
            Product product7 = new Product("Wine", 4.05, Category.FOOD, 22);

            productRepository.saveAll(
                    List.of(product1,product2,product3,product4,product5,product6,product7)
            );


        };
    }

    @Bean
    CommandLineRunner commandLineRunner1(CustomerRepository customerRepository){
        return args -> {
            Customer customer1 = new Customer("Hud", "Saidi", "hbuhero@gmail.com");

            Customer customer2 = new Customer("Nuh", "Saidi", "nbuhero@gmail.com");

            Customer customer3 = new Customer("Yusuf", "Jongo", "maporinib@gmail.com");

            customerRepository.saveAll(
                    List.of(customer1,customer2,customer3)
            );
        };
    }


}
