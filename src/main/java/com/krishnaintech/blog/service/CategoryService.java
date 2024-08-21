package com.krishnaintech.blog.service;

import com.krishnaintech.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
     CategoryDto createCategory(CategoryDto categoryDto);
     CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
     CategoryDto getCategoryById(Integer categoryId);
     List<CategoryDto> getAllCategory();
     void DeleteCategory(Integer categoryId);
}
