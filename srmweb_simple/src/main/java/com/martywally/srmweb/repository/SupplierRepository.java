package com.martywally.srmweb.repository;

import com.martywally.srmweb.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}