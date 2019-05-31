package com.ayc.api.v1.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ayc.api.v1.model.CustomerDTO;
import com.ayc.domain.Customer;

public class CustomerMapperTest {

	CustomerMapper mapper = CustomerMapper.INSTANCE;
	
	private static long id = 1L;
	private static String FIRST_NAME = "first name";
	private static String LAST_NAME = "last name";
	
	@Test
	public void customerToCustomerDTOTest() {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setFirstName(FIRST_NAME);
		customer.setLastName(LAST_NAME);
		
		CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);
		
		assertNotNull(customerDTO);
		assertEquals(Long.valueOf(id), customerDTO.getId());
		assertEquals(FIRST_NAME, customerDTO.getFirstName());
		assertEquals(LAST_NAME, customerDTO.getLastName());
	}
	
	@Test
	public void customerDTOToCustomerTest() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(id);
		customerDTO.setFirstName(FIRST_NAME);
		customerDTO.setLastName(LAST_NAME);
		
		Customer customer = mapper.customerDTOToCustomer(customerDTO);
		
		assertNotNull(customer);
		assertEquals(Long.valueOf(id), customer.getId());
		assertEquals(FIRST_NAME, customer.getFirstName());
		assertEquals(LAST_NAME, customer.getLastName());
	}
}
