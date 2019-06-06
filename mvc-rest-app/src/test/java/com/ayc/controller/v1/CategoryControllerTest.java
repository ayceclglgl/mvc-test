package com.ayc.controller.v1;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ayc.api.v1.model.CategoryDTO;
import com.ayc.controller.RestResponseEntityExceptionHandler;
import com.ayc.exception.ResourceNotFoundException;
import com.ayc.service.CategoryService;


public class CategoryControllerTest {
	
	@InjectMocks // --> kimlere nelere uygulanabiliyor
	CategoryController controller;
	
	@Mock
	CategoryService service;
	
	MockMvc mvc;
	
	private static String NAME = "Name";
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(RestResponseEntityExceptionHandler.class)
				.build();
	}
	
	@Test
	public void testListCategories() throws Exception {
		List<CategoryDTO> catList = Arrays.asList(new CategoryDTO(), new CategoryDTO());
		when(service.getAllCategories()).thenReturn(catList);
		
		
		mvc.perform(get("/api/v1/categories/")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.categories", hasSize(2))); //$ means root
	}
	
	@Test
	public void testGetByNameCategories() throws Exception {
		CategoryDTO cDto = new CategoryDTO();
		cDto.setName(NAME);
		
		when(service.getCategoryByName(anyString())).thenReturn(cDto);
		
		mvc.perform(get("/api/v1/categories/" + NAME)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", equalTo(NAME)));
	}
	
	@Test
	public void testGetByNameNotFound() throws Exception {
		String notFound = "notFound";
		
		when(service.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);
		
		mvc.perform(get("/api/v1/categories/" + notFound)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
	

}
