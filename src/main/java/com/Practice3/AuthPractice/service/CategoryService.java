package com.Practice3.AuthPractice.service;

import com.Practice3.AuthPractice.model.Category;
import com.Practice3.AuthPractice.payload.CategoryDTO;
import com.Practice3.AuthPractice.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}

