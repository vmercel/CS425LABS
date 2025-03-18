package com.martywally.srmweb.repository;

import com.martywally.srmweb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySupplierSupplierId(Integer supplierId);
    List<Product> findAllByOrderByNameAsc();
}
