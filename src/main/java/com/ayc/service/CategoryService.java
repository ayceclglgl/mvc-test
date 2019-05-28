package com.ayc.service;

import java.util.List;

import com.ayc.api.v1.model.CategoryDTO;

public interface CategoryService {
	List<CategoryDTO> getAllCategories();
	CategoryDTO getCategoryByName(String name);
}
