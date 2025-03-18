// edu.miu.cs.cs425.revision1.entity.Category.java
package edu.miu.cs.cs425.revision1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;
}