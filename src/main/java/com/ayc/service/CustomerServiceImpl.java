package com.ayc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ayc.api.v1.mapper.CustomerMapper;
import com.ayc.api.v1.model.CustomerDTO;
import com.ayc.domain.Customer;
import com.ayc.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	CustomerRepository customerRepository;
	CustomerMapper customerMapper;
	private final static String CUSTOMER_URL_PREFIX = "/api/v1/customer/";
	
	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}
	
	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll()
		.stream()
		.map(customer -> {
			CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
			customerDTO.setCustomerUrl(CUSTOMER_URL_PREFIX + customer.getId());
			return customerDTO;
		})
		.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		return customerRepository.findById(id)
		.map(customerMapper::customerToCustomerDTO)
		.orElseThrow(RuntimeException::new);
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
		return saveCustomer(customerMapper.customerDTOToCustomer(customerDTO));
	}

	@Override
	public CustomerDTO updateCustomerByDTO(Long id, CustomerDTO customerDTO) {
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		customer.setId(id);
		return saveCustomer(customer);
	}
	
	private CustomerDTO saveCustomer(Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		CustomerDTO returnedDTO = customerMapper.customerToCustomerDTO(savedCustomer);
		returnedDTO.setCustomerUrl(CUSTOMER_URL_PREFIX + savedCustomer.getId());
		return returnedDTO;
	}
}
