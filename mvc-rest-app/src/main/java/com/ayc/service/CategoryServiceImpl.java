package com.ayc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ayc.api.v1.mapper.CategoryMapper;
import com.ayc.api.v1.model.CategoryDTO;
import com.ayc.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	CategoryRepository categoryRepository;
	CategoryMapper categoryMapper;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}
	
	
	@Override
	public List<CategoryDTO> getAllCategories() {
		return categoryRepository.findAll()
		.stream()
		.map(categoryMapper::categoryToCategoryDTO)
		.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
	}

}
