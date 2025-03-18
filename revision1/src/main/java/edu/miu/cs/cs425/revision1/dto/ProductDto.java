// edu.miu.cs.cs425.revision1.dto.ProductDto.java
package edu.miu.cs.cs425.revision1.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private List<Integer> categoryIds; // IDs of associated categories
}