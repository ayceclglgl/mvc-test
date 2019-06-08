package com.ayc.api.v1.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ayc.api.v1.mapper.CategoryMapper;
import com.ayc.api.v1.model.CategoryDTO;
import com.ayc.domain.Category;

public class CategoryMapperTest {
	
	CategoryMapper mapper = CategoryMapper.INSTANCE;
	
	private static String NAME = "Name";
	
	@Test
	public void categoryToCategoryDTOTest() {
		Category category = new Category();
		category.setName(NAME);
		
		
		CategoryDTO cDto = mapper.categoryToCategoryDTO(category);
		
		assertNotNull(cDto);
		assertEquals(NAME, cDto.getName());
	}

}
