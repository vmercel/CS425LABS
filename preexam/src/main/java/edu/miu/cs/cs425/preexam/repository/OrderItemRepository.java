package edu.miu.cs.cs425.preexam.repository;

import edu.miu.cs.cs425.preexam.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}