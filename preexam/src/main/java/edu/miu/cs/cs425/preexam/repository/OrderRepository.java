package edu.miu.cs.cs425.preexam.repository;

import edu.miu.cs.cs425.preexam.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}