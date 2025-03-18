// edu.miu.cs.cs425.revision1.entity.OrderItem.java
package edu.miu.cs.cs425.revision1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double quantity;
    private Double unitPrice;
    private Double costPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}