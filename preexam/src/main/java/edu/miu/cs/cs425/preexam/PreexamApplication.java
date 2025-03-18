package edu.miu.cs.cs425.preexam;

import edu.miu.cs.cs425.preexam.model.Category;
import edu.miu.cs.cs425.preexam.model.Order;
import edu.miu.cs.cs425.preexam.model.OrderItem;
import edu.miu.cs.cs425.preexam.model.Product;
import edu.miu.cs.cs425.preexam.repository.CategoryRepository;
import edu.miu.cs.cs425.preexam.repository.OrderItemRepository;
import edu.miu.cs.cs425.preexam.repository.OrderRepository;
import edu.miu.cs.cs425.preexam.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class PreexamApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreexamApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataInitializer(CategoryRepository categoryRepository,
                                             ProductRepository productRepository,
                                             OrderItemRepository orderItemRepository,
                                             OrderRepository orderRepository) {
        return args -> {
            // Clear existing data (optional, for a clean start)
            categoryRepository.deleteAll();
            productRepository.deleteAll();
            orderItemRepository.deleteAll();
            orderRepository.deleteAll();

            // Initialize Categories
            Category electronics = new Category(null, "Electronics", null);
            Category books = new Category(null, "Books", null);
            categoryRepository.saveAll(Arrays.asList(electronics, books));

            // Initialize Products
            Product laptop = new Product(null, "Laptop", "High-performance laptop", electronics, null);
            Product book = new Product(null, "Java Programming", "Beginner guide to Java", books, null);
            productRepository.saveAll(Arrays.asList(laptop, book));

            // Initialize Orders
            Order order1 = new Order(null, LocalDate.now(), 1200.0, 120.0, 1320.0, null);
            Order order2 = new Order(null, LocalDate.now().minusDays(1), 50.0, 5.0, 55.0, null);
            orderRepository.saveAll(Arrays.asList(order1, order2));

            // Initialize OrderItems
            OrderItem orderItem1 = new OrderItem(null, 1.0, 1200.0, 1000.0, laptop, order1);
            OrderItem orderItem2 = new OrderItem(null, 2.0, 25.0, 20.0, book, order2);
            orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2));

            // Link OrderItems to Orders
            order1.setOrderItems(Arrays.asList(orderItem1));
            order2.setOrderItems(Arrays.asList(orderItem2));
            orderRepository.saveAll(Arrays.asList(order1, order2));

            // Link Products to OrderItems (already done via constructor, but ensuring consistency)
            laptop.setOrderItems(Arrays.asList(orderItem1));
            book.setOrderItems(Arrays.asList(orderItem2));
            productRepository.saveAll(Arrays.asList(laptop, book));

            System.out.println("Database initialized with sample data!");
        };
    }
}