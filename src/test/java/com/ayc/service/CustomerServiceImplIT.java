package com.ayc.service;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ayc.api.v1.mapper.CustomerMapper;
import com.ayc.api.v1.model.CustomerDTO;
import com.ayc.bootstrap.Bootstrap;
import com.ayc.domain.Customer;
import com.ayc.repositories.CategoryRepository;
import com.ayc.repositories.CustomerRepository;
import com.ayc.repositories.VendorRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	VendorRepository vendorRepository;
	
	CustomerService service;
	
	@Before
	public void setUp() throws Exception {
		
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
		bootstrap.run();
		
		service = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
	}
	
	 @Test
	 public void patchCustomerUpdateFirstName() throws Exception {
		 String updatedFirstName = "updatedFirstName";
		 long id = getFirstCustomerId();
		 
		 Customer customerOriginal = customerRepository.getOne(id); // get reference
		 String originalFirstName = customerOriginal.getFirstName();
		 String originalLastName = customerOriginal.getLastName();
		 
		 CustomerDTO customerDTO = new CustomerDTO();
		 customerDTO.setId(id);
		 customerDTO.setFirstName(updatedFirstName);
		 
		service.patchCustomer(id, customerDTO);
		
		Customer patchCustomerDTO = customerRepository.findById(id).get();
		
		
		assertNotNull(patchCustomerDTO);
		assertEquals(updatedFirstName, patchCustomerDTO.getFirstName());
		assertThat(originalFirstName, not(equalTo(patchCustomerDTO.getFirstName())));
		assertThat(originalLastName, equalTo(patchCustomerDTO.getLastName()));
		 
	 }
	 
	@Test
	 public void patchCustomerUpdateLastName() throws Exception {
		String updatedLastName = "updatedLastName";
		long id = getFirstCustomerId();
		
		Customer customerOriginal = customerRepository.getOne(id); // get reference
		String originalFirstName = customerOriginal.getFirstName();
		String originalLastName = customerOriginal.getLastName();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setLastName(updatedLastName);
		
		service.patchCustomer(id, customerDTO);
		
		Customer patchCustomerDTO = customerRepository.findById(id).get();
		
		assertNotNull(patchCustomerDTO);
		assertEquals(updatedLastName, patchCustomerDTO.getLastName());
		assertThat(originalLastName, not(patchCustomerDTO.getLastName()));
		assertThat(originalFirstName, equalTo(patchCustomerDTO.getFirstName()));
	 }
	
	@Test
	public void testDeleteCustomerById() {
		long id = getFirstCustomerId();
		service.deleteCustomerById(id);
		
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		assertEquals(Optional.empty(), optionalCustomer);
	}
	
	private long getFirstCustomerId() {
		return customerRepository.findAll().get(0).getId();
	}


}
