package com.ayc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ayc.api.v1.mapper.CategoryMapper;
import com.ayc.api.v1.model.CategoryDTO;
import com.ayc.domain.Category;
import com.ayc.repositories.CategoryRepository;

public class CategoryServiceTest {
	
	CategoryService service;
	@Mock
	CategoryRepository categoryRepository;
	
	private static long id = 1L;
	private static String NAME = "Name";
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
	}
	
	@Test
	public void getAllCategoriesTest() {
		List<Category> catList = Arrays.asList(new Category(), new Category());		
		when(categoryRepository.findAll()).thenReturn(catList);
		
		List<CategoryDTO> list = service.getAllCategories();
		assertEquals(catList.size(), list.size());
	}
	
	
	@Test
	public void getCategoryByNameTest() {
		Category category = new Category();
		category.setId(id);
		category.setName(NAME);
		when(categoryRepository.findByName(anyString())).thenReturn(category);
		
		
		CategoryDTO cDto = service.getCategoryByName(NAME);
		
		assertNotNull(cDto);
		assertEquals(Long.valueOf(id), cDto.getId());
		assertEquals(NAME, cDto.getName());
	}

}
