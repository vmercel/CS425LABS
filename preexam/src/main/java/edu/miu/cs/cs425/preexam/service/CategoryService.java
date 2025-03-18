package edu.miu.cs.cs425.preexam.service;

import edu.miu.cs.cs425.preexam.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Integer id);
}