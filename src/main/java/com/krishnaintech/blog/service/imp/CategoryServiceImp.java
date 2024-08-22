package com.krishnaintech.blog.service.imp;

import com.krishnaintech.blog.entity.Category;
import com.krishnaintech.blog.exceptions.ResourceNotFoundException;
import com.krishnaintech.blog.payload.CategoryDto;
import com.krishnaintech.blog.repository.CategoryRepo;
import com.krishnaintech.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    private final ModelMapper modelMapper;
    private final CategoryRepo categoryRepo;
    public CategoryServiceImp(ModelMapper modelMapper, CategoryRepo categoryRepo) {
        this.modelMapper = modelMapper;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() ->new ResourceNotFoundException("Category","Category Id", categoryId));
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDetail(categoryDto.getCategoryDetail());
        Category updatedCategory = categoryRepo.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() ->new ResourceNotFoundException("Category","Category Id", categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> listOfCategories = categoryRepo.findAll();
        return listOfCategories.stream().map((category) -> modelMapper.map(category, CategoryDto.class)).toList();
    }

    @Override
    public void DeleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() ->new ResourceNotFoundException("Category","Category Id", categoryId));
        categoryRepo.delete(category);
    }
}
