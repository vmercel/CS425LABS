package edu.miu.cs.cs425.revision1.service;

import edu.miu.cs.cs425.revision1.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto createCategory(CategoryDto categoryDto);
}