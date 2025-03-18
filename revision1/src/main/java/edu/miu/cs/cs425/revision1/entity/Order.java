// edu.miu.cs.cs425.revision1.entity.Order.java
package edu.miu.cs.cs425.revision1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dateOrdered;
    private Double totalCostPrice;
    private Double totalTax;
    private Double totalAmountCharged;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}