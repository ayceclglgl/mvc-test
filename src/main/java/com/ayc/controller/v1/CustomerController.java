package com.ayc.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ayc.api.v1.model.CustomerDTO;
import com.ayc.api.v1.model.CustomerListDTO;
import com.ayc.service.CustomerService;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

	CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public ResponseEntity<CustomerListDTO> getAllCustomers(){
		return new ResponseEntity<CustomerListDTO>(
				new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CustomerDTO> getCustomerByID(@PathVariable("id") String id){
		Long customerId = Long.valueOf(id);
		
		return new ResponseEntity<CustomerDTO>(
				customerService.getCustomerById(customerId), HttpStatus.OK);
	}
	
	
}
