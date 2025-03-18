package edu.miu.cs.cs425.preexam.repository;

import edu.miu.cs.cs425.preexam.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}