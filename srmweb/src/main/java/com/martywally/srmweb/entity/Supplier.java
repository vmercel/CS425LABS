package com.martywally.srmweb.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierId;

    @Column(nullable = false)
    private String name;

    private String contactPhone;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Supplier() {}

    public Supplier(String name, String contactPhone) {
        this.name = name;
        this.contactPhone = contactPhone;
    }

    // Getters and Setters
    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}