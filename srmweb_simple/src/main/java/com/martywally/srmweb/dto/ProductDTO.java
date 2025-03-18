package com.martywally.srmweb.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductDTO {
    private Long productId;
    private String name;
    private BigDecimal unitPrice;
    private Integer quantityInStock;
    private LocalDate dateSupplied;
    private SupplierDTO supplier;

    // Constructors
    public ProductDTO() {}

    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public Integer getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(Integer quantityInStock) { this.quantityInStock = quantityInStock; }
    public LocalDate getDateSupplied() { return dateSupplied; }
    public void setDateSupplied(LocalDate dateSupplied) { this.dateSupplied = dateSupplied; }
    public SupplierDTO getSupplier() { return supplier; }
    public void setSupplier(SupplierDTO supplier) { this.supplier = supplier; }
}