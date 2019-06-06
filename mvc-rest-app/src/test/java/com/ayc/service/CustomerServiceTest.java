package com.ayc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ayc.api.v1.mapper.CustomerMapper;
import com.ayc.api.v1.model.CustomerDTO;
import com.ayc.domain.Customer;
import com.ayc.repositories.CustomerRepository;



public class CustomerServiceTest {
	@Mock
	CustomerRepository repository;
	
	CustomerService service;
	
	private static long id = 1L;
	private static String NAME = "Name";
	private final static String CUSTOMER_URL_PREFIX = "/api/v1/customer/";
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new CustomerServiceImpl(repository, CustomerMapper.INSTANCE);
	}
	
	@Test
	public void testGetAllCustomers() {
		Customer customer = new Customer();
		customer.setFirstName(NAME);
		customer.setId(id);
		when(repository.findAll()).thenReturn(Arrays.asList(customer));
		
		List<CustomerDTO> list = service.getAllCustomers();
		assertNotNull(list);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testGetCustomerById() {
		Customer customer = new Customer();
		customer.setFirstName(NAME);
		customer.setId(id);
		when(repository.findById(any())).thenReturn(Optional.of(customer));
		
		CustomerDTO customerDTO = service.getCustomerById(anyLong());
		assertNotNull(customerDTO);
		assertEquals(Long.valueOf(id), customerDTO.getId());
		assertEquals(NAME, customerDTO.getFirstName());
	
	}
	
	@Test
	public void testCreateNewCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName(NAME);
		customerDTO.setId(id);
		
		Customer customer = new Customer();
		customer.setFirstName(NAME);
		customer.setId(id);
		when(repository.save(any())).thenReturn(customer);
		
		CustomerDTO savedCustomerDTO = service.createNewCustomer(customerDTO);
		
		assertNotNull(savedCustomerDTO);
		assertEquals(Long.valueOf(id), savedCustomerDTO.getId());
		assertEquals(NAME, savedCustomerDTO.getFirstName());
		assertEquals(CUSTOMER_URL_PREFIX + id, savedCustomerDTO.getCustomerUrl());
	}
	
	@Test
	public void testUpdateCustomerByDTO(){
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName(NAME);
		customerDTO.setId(id);
		
		Customer customer = new Customer();
		customer.setFirstName(NAME);
		customer.setId(id);
		when(repository.save(any())).thenReturn(customer);
		
		CustomerDTO updatedCustomerDTO = service.updateCustomerByDTO(id, customerDTO);
		
		assertNotNull(updatedCustomerDTO);
		assertEquals(Long.valueOf(id), updatedCustomerDTO.getId());
		assertEquals(NAME, updatedCustomerDTO.getFirstName());
		assertEquals(CUSTOMER_URL_PREFIX + id, updatedCustomerDTO.getCustomerUrl());
		
	}
	
	@Test
	public void testPatchCustomer(){
		String updatedFirstName = NAME + "patch";
		Customer customer = new Customer();
		customer.setFirstName(NAME);
		
		Customer updatedCustomer = new Customer();
		updatedCustomer.setFirstName(updatedFirstName);
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName(updatedFirstName);
		
		when(repository.findById(any())).thenReturn(Optional.of(customer));
		when(repository.save(any())).thenReturn(updatedCustomer);
		
		CustomerDTO patchCustomerDTO = service.updateCustomerByDTO(id, customerDTO);
		
		assertNotNull(patchCustomerDTO);
		assertEquals(updatedFirstName, patchCustomerDTO.getFirstName());
	}
	
	@Test
	public void testDeleteCustomer() {
		service.deleteCustomerById(anyLong());
		verify(repository).deleteById(anyLong());
	}

}
