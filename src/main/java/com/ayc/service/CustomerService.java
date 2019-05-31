package com.ayc.service;

import java.util.List;

import com.ayc.api.v1.model.CustomerDTO;

public interface CustomerService {
	List<CustomerDTO> getAllCustomers();
	CustomerDTO getCustomerById(Long id);
	CustomerDTO createNewCustomer(CustomerDTO customerDTO);
	CustomerDTO updateCustomerByDTO(Long id, CustomerDTO customerDTO);

}
