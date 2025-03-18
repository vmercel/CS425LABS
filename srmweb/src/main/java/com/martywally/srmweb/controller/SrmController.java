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
                .map(this::convertToProductDTOWithoutSupplier)
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
        if (product.getSupplier() != null) {
            SupplierDTO supplierDTO = new SupplierDTO();
            supplierDTO.setSupplierId(product.getSupplier().getSupplierId());
            supplierDTO.setName(product.getSupplier().getName());
            supplierDTO.setContactPhone(product.getSupplier().getContactPhone());
            dto.setSupplier(supplierDTO);
        }
        return dto;
    }

    private ProductDTO convertToProductDTOWithoutSupplier(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setUnitPrice(product.getUnitPrice());
        dto.setQuantityInStock(product.getQuantityInStock());
        dto.setDateSupplied(product.getDateSupplied());
        return dto;
    }

    @PostConstruct
    public void initData() {
        Supplier iowaFarms = new Supplier("Iowa Farms", "(641) 451-0009");
        Supplier hallmarkAgro = new Supplier("Hallmark Agro, Inc.", null);

        supplierRepository.save(iowaFarms);
        supplierRepository.save(hallmarkAgro);

        Product apples = new Product("Santa sweet apples", new BigDecimal("1.09"), 124,
                LocalDate.parse("2023-05-31"));
        apples.setSupplier(iowaFarms);

        Product drumsticks = new Product("Chicken drumsticks", new BigDecimal("2.25"), 18,
                LocalDate.parse("2023-04-10"));
        drumsticks.setSupplier(iowaFarms);

        Product bananas = new Product("Dole Bananas", new BigDecimal("0.55"), 1097,
                LocalDate.parse("2023-05-15"));
        bananas.setSupplier(hallmarkAgro);

        productRepository.saveAll(List.of(apples, drumsticks, bananas));
    }

    @Operation(summary = "Get all suppliers", description = "Retrieve list of all suppliers with their products")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of suppliers",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SupplierDTO.class)))
    @GetMapping("/suppliers")
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(this::convertToSupplierDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get all products", description = "Retrieve list of all products sorted by name")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of products",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ProductDTO.class)))
    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAllByOrderByNameAsc().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get products by supplier ID",
            description = "Retrieve list of products for a specific supplier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of products",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "404", description = "Supplier not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/product/get/suppler/{supplierId}")
    public ResponseEntity<?> getProductsBySupplierId(
            @Parameter(description = "ID of supplier to fetch products for")
            @PathVariable Integer supplierId) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);

        if (supplier.isPresent()) {
            List<ProductDTO> products = productRepository.findBySupplierSupplierId(supplierId).stream()
                    .map(this::convertToProductDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(products);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Supplier with ID " + supplierId + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}