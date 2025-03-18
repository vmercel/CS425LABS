package com.martywally.srmweb.dto;

import java.util.ArrayList;
import java.util.List;

public class SupplierDTO {
    private Integer supplierId;
    private String name;
    private String contactPhone;
    private List<ProductDTO> products = new ArrayList<>();

    // Constructors
    public SupplierDTO() {}

    // Getters and Setters
    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public List<ProductDTO> getProducts() { return products; }
    public void setProducts(List<ProductDTO> products) { this.products = products; }
}