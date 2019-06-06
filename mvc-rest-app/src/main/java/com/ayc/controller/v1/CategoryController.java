package com.ayc.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ayc.api.v1.model.CategoryDTO;
import com.ayc.api.v1.model.CategoryListDTO;
import com.ayc.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {
	
	CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CategoryListDTO getAllCategories(){
		return new CategoryListDTO(categoryService.getAllCategories());
	}
	
	@GetMapping("{name}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryDTO getCategoryByName(@PathVariable("name") String name){
		return categoryService.getCategoryByName(name);
	}
}
