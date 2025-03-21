package com.martywally.srmweb.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    private Long productId;
    private String name;
    private BigDecimal unitPrice;
    private Integer quantityInStock;
    private LocalDate dateSupplied;
    private List<SupplierDTO> suppliers = new ArrayList<>();

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
    public List<SupplierDTO> getSuppliers() { return suppliers; }
    public void setSuppliers(List<SupplierDTO> suppliers) { this.suppliers = suppliers; }
}