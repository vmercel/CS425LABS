package edu.miu.cs.cs425.preexam.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer categoryId;
}