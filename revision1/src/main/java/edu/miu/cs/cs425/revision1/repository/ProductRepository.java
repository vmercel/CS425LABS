// edu.miu.cs.cs425.revision1.repository.ProductRepository.java
package edu.miu.cs.cs425.revision1.repository;

import edu.miu.cs.cs425.revision1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}