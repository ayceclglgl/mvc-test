package com.ayc.controller.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.ayc.controller.RestResponseEntityExceptionHandler;
import com.ayc.exception.ResourceNotFoundException;
import com.ayc.model.CustomerDTO;
import com.ayc.service.CustomerService;

public class CustomerControllerTest extends AbstractRestController{
	
	@InjectMocks
	CustomerController controller;
	
	@Mock
	CustomerService service;
	
	MockMvc mvc;
	
	private static final Long ID = 1L; 
	private static final String FIRST_NAME = "FIRST_NAME"; 
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(RestResponseEntityExceptionHandler.class)
				.build();
	}
	
	@Test
	public void testGetAllCustomers() throws Exception {
		List<CustomerDTO> list = Arrays.asList(new CustomerDTO(), new CustomerDTO());
		when(service.getAllCustomers()).thenReturn(list);
		
		mvc.perform(get("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.customers", hasSize(2))); //$ means root
		
	}
	
	@Test
	public void testGetCustomerByID() throws Exception {
		CustomerDTO cDto = new CustomerDTO();
		cDto.setFirstName(FIRST_NAME);
		
		when(service.getCustomerById(anyLong())).thenReturn(cDto);
		
		mvc.perform(get("/api/v1/customers/" + ID)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
		
	}
	
	@Test
	public void testCreateNewCustomer() throws Exception{
		CustomerDTO cDto = new CustomerDTO();
		cDto.setFirstName(FIRST_NAME);
		
		when(service.createNewCustomer(cDto)).thenReturn(cDto);
		
		mvc.perform(post("/api/v1/customers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(cDto)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
	}
	
	@Test
	public void testUpdateCustomer() throws Exception{
		CustomerDTO cDto = new CustomerDTO();
		cDto.setFirstName(FIRST_NAME);
		
		when(service.updateCustomerByDTO(ID, cDto)).thenReturn(cDto);
		
		mvc.perform(put("/api/v1/customers/" + ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(cDto)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
	}
	
	
	@Test
	public void testPatchCustomer() throws Exception{
		CustomerDTO cDto = new CustomerDTO();
		cDto.setFirstName(FIRST_NAME);
		
		when(service.patchCustomer(ID, cDto)).thenReturn(cDto);
		
		mvc.perform(patch("/api/v1/customers/" + ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(cDto)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
	}
	
	@Test
	public void testDeleteCustomer() throws Exception {
		mvc.perform(delete("/api/v1/customers/" + ID))
		.andExpect(status().isOk());
		
		
		verify(service).deleteCustomerById(anyLong());
	}

	@Test
	public void testNotFoundException() throws Exception {
		when(service.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);
		
		mvc.perform(get("/api/v1/customers/" + ID)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

}
