package com.ayc.api.v1.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ayc.api.v1.mapper.CategoryMapper;
import com.ayc.api.v1.model.CategoryDTO;
import com.ayc.domain.Category;

public class CategoryMapperTest {
	
	CategoryMapper mapper = CategoryMapper.INSTANCE;
	
	private static long id = 1L;
	private static String NAME = "Name";
	
	@Test
	public void categoryToCategoryDTOTest() {
		Category category = new Category();
		category.setId(id);
		category.setName(NAME);
		
		
		CategoryDTO cDto = mapper.categoryToCategoryDTO(category);
		
		assertNotNull(cDto);
		assertEquals(Long.valueOf(id), cDto.getId());
		assertEquals(NAME, cDto.getName());
	}

}
