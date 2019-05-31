package com.ayc.controller.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestController {
	
	public static String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}

}