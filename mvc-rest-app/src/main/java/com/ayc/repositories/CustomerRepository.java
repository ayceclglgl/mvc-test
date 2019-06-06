package com.ayc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayc.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
