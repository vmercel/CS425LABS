package com.martywally.srmweb.controller;

import com.martywally.srmweb.dto.ProductDTO;
import com.martywally.srmweb.dto.SupplierDTO;
import com.martywally.srmweb.entity.Product;
import com.martywally.srmweb.entity.Supplier;
import com.martywally.srmweb.repository.ProductRepository;
import com.martywally.srmweb.repository.SupplierRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/srmweb/api")
@Tag(name = "SRM API", description = "Supplier Relationship Management API endpoints")
public class SrmController {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductRepository productRepository;

    // Conversion methods
    private SupplierDTO convertToSupplierDTO(Supplier supplier) {
        SupplierDTO dto = new SupplierDTO();
        dto.setSupplierId(supplier.getSupplierId());
        dto.setName(supplier.getName());
        dto.setContactPhone(supplier.getContactPhone());
        dto.setProducts(supplier.getProducts().stream()
                .map(this::convertToProductDTOWithoutSuppliers)
                .collect(Collectors.toList()));
        return dto;
    }

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setUnitPrice(product.getUnitPrice());
        dto.setQuantityInStock(product.getQuantityInStock());
        dto.setDateSupplied(product.getDateSupplied());
        dto.setSuppliers(product.getSuppliers().stream()
                .map(this::convertToSupplierDTOWithoutProducts)
                .collect(Collectors.toList()));
        return dto;
    }

    private ProductDTO convertToProductDTOWithoutSuppliers(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setUnitPrice(product.getUnitPrice());
        dto.setQuantityInStock(product.getQuantityInStock());
        dto.setDateSupplied(product.getDateSupplied());
        return dto;
    }

    private SupplierDTO convertToSupplierDTOWithoutProducts(Supplier supplier) {
        SupplierDTO dto = new SupplierDTO();
        dto.setSupplierId(supplier.getSupplierId());
        dto.setName(supplier.getName());
        dto.setContactPhone(supplier.getContactPhone());
        return dto;
    }

    @PostConstruct
    @Transactional
    public void initData() {
        // First save suppliers without relationships
        Supplier iowaFarms = new Supplier("Iowa Farms", "(641) 451-0009");
        Supplier hallmarkAgro = new Supplier("Hallmark Agro, Inc.", null);
        supplierRepository.saveAll(List.of(iowaFarms, hallmarkAgro));

        // Then save products without relationships
        Product apples = new Product("Santa sweet apples", new BigDecimal("1.09"), 124,
                LocalDate.parse("2023-05-31"));
        Product drumsticks = new Product("Chicken drumsticks", new BigDecimal("2.25"), 18,
                LocalDate.parse("2023-04-10"));
        Product bananas = new Product("Dole Bananas", new BigDecimal("0.55"), 1097,
                LocalDate.parse("2023-05-15"));
        productRepository.saveAll(List.of(apples, drumsticks, bananas));

        // Establish relationships (only need to set on owning side since we're in a transaction)
        iowaFarms.getProducts().add(apples);
        iowaFarms.getProducts().add(drumsticks);
        hallmarkAgro.getProducts().add(bananas);

        // Save the owning side to persist relationships
        supplierRepository.saveAll(List.of(iowaFarms, hallmarkAgro));

        // Since Supplier is the owning side, we don't need to explicitly save products
        // The bidirectional relationship will be maintained by Hibernate
    }

    // Supplier CRUD
    @Operation(summary = "Get all suppliers")
    @GetMapping("/suppliers")
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(this::convertToSupplierDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get supplier by ID")
    @GetMapping("/suppliers/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable Integer id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.isPresent()
                ? ResponseEntity.ok(convertToSupplierDTO(supplier.get()))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Supplier with ID " + id + " not found"));
    }

    @Operation(summary = "Create a new supplier")
    @PostMapping("/suppliers")
    @Transactional
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier(supplierDTO.getName(), supplierDTO.getContactPhone());
        Supplier saved = supplierRepository.save(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToSupplierDTO(saved));
    }

    @Operation(summary = "Update a supplier")
    @PutMapping("/suppliers/{id}")
    @Transactional
    public ResponseEntity<?> updateSupplier(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO) {
        Optional<Supplier> existing = supplierRepository.findById(id);
        if (existing.isPresent()) {
            Supplier supplier = existing.get();
            supplier.setName(supplierDTO.getName());
            supplier.setContactPhone(supplierDTO.getContactPhone());
            return ResponseEntity.ok(convertToSupplierDTO(supplierRepository.save(supplier)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Supplier with ID " + id + " not found"));
    }

    @Operation(summary = "Delete a supplier")
    @DeleteMapping("/suppliers/{id}")
    @Transactional
    public ResponseEntity<?> deleteSupplier(@PathVariable Integer id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) {
            supplierRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Supplier with ID " + id + " not found"));
    }

    // Product CRUD
    @Operation(summary = "Get all products")
    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAllByOrderByNameAsc().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get product by ID")
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.isPresent()
                ? ResponseEntity.ok(convertToProductDTO(product.get()))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Product with ID " + id + " not found"));
    }

    @Operation(summary = "Create a new product")
    @PostMapping("/products")
    @Transactional
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product(
                productDTO.getName(),
                productDTO.getUnitPrice(),
                productDTO.getQuantityInStock(),
                productDTO.getDateSupplied()
        );
        Product saved = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToProductDTO(saved));
    }

    @Operation(summary = "Update a product")
    @PutMapping("/products/{id}")
    @Transactional
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Optional<Product> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            Product product = existing.get();
            product.setName(productDTO.getName());
            product.setUnitPrice(productDTO.getUnitPrice());
            product.setQuantityInStock(productDTO.getQuantityInStock());
            product.setDateSupplied(productDTO.getDateSupplied());
            return ResponseEntity.ok(convertToProductDTO(productRepository.save(product)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Product with ID " + id + " not found"));
    }

    @Operation(summary = "Delete a product")
    @DeleteMapping("/products/{id}")
    @Transactional
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Product with ID " + id + " not found"));
    }

    // Relationship management
    @Operation(summary = "Get products by supplier ID")
    @GetMapping("/product/get/suppler/{supplierId}")
    public ResponseEntity<?> getProductsBySupplierId(@PathVariable Integer supplierId) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isPresent()) {
            List<ProductDTO> products = supplier.get().getProducts().stream()
                    .map(this::convertToProductDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Supplier with ID " + supplierId + " not found"));
    }

    @Operation(summary = "Add product to supplier")
    @PostMapping("/suppliers/{supplierId}/products/{productId}")
    @Transactional
    public ResponseEntity<?> addProductToSupplier(
            @PathVariable Integer supplierId,
            @PathVariable Long productId) {
        Optional<Supplier> supplierOpt = supplierRepository.findById(supplierId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (supplierOpt.isPresent() && productOpt.isPresent()) {
            Supplier supplier = supplierOpt.get();
            Product product = productOpt.get();
            supplier.getProducts().add(product);
            // No need to add to product.suppliers since Supplier is owning side
            supplierRepository.save(supplier);
            return ResponseEntity.ok(convertToSupplierDTO(supplier));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Supplier or Product not found"));
    }

    @Operation(summary = "Remove product from supplier")
    @DeleteMapping("/suppliers/{supplierId}/products/{productId}")
    @Transactional
    public ResponseEntity<?> removeProductFromSupplier(
            @PathVariable Integer supplierId,
            @PathVariable Long productId) {
        Optional<Supplier> supplierOpt = supplierRepository.findById(supplierId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (supplierOpt.isPresent() && productOpt.isPresent()) {
            Supplier supplier = supplierOpt.get();
            Product product = productOpt.get();
            supplier.getProducts().remove(product);
            // No need to remove from product.suppliers since Supplier is owning side
            supplierRepository.save(supplier);
            return ResponseEntity.ok(convertToSupplierDTO(supplier));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Supplier or Product not found"));
    }
}