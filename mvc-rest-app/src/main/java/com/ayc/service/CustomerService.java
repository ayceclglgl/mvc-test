package com.ayc.service;

import java.util.List;

import com.ayc.model.CustomerDTO;


public interface CustomerService {
	List<CustomerDTO> getAllCustomers();
	CustomerDTO getCustomerById(Long id);
	CustomerDTO createNewCustomer(CustomerDTO customerDTO);
	CustomerDTO updateCustomerByDTO(Long id, CustomerDTO customerDTO);
	CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
	void deleteCustomerById(Long id);

}
