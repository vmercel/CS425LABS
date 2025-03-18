package edu.miu.cs.cs425.revision1.service;

import edu.miu.cs.cs425.revision1.dto.ProductDto;
import edu.miu.cs.cs425.revision1.entity.Product;
import edu.miu.cs.cs425.revision1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        // Handle category associations (requires additional logic)
        product = productRepository.save(product);
        return convertToDto(product);
    }

    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        // Map category IDs if needed
        return dto;
    }
}