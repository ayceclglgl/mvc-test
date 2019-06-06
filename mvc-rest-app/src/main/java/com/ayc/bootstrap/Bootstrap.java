package com.ayc.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ayc.domain.Category;
import com.ayc.domain.Customer;
import com.ayc.domain.Vendor;
import com.ayc.repositories.CategoryRepository;
import com.ayc.repositories.CustomerRepository;
import com.ayc.repositories.VendorRepository;



@Component
public class Bootstrap implements CommandLineRunner{

	private CategoryRepository categoryRepository;
	private CustomerRepository customerRepository;
	private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, 
    		VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }
    
	@Override
	public void run(String... args) throws Exception {
		loadCategories();
		loadCustomers();
		loadVendors();
		
	}
	
	
	private void loadVendors() {
		Vendor vendor = new Vendor();
		vendor.setName("Vendor1");
		
		Vendor vendor2 = new Vendor();
		vendor2.setName("Vendor2");
		
		vendorRepository.save(vendor);
		vendorRepository.save(vendor2);
		
		System.out.println("Vendors Loaded = " + vendorRepository.count() );
		
	}

	private void loadCategories() {
		Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        System.out.println("Categories Loaded = " + categoryRepository.count() );
	}
	
	
	private void loadCustomers() {
		Customer customer1 = new Customer();
		customer1.setId(1L);
		customer1.setFirstName("Mike");
		customer1.setLastName("Johnat");
		customerRepository.save(customer1);
		
		Customer customer2 = new Customer();
		customer2.setId(2L);
		customer2.setFirstName("Sarah");
		customer2.setLastName("Milk");
		customerRepository.save(customer2);
		
		System.out.println("Customers Loaded = " + customerRepository.count() );
	}

}
