package com.ayc.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ayc.api.v1.model.VendorDTO;
import com.ayc.api.v1.model.VendorDTOList;
import com.ayc.service.VendorService;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
	public static final String BASE_URL = "/api/v1/vendors";
	
	VendorService vendorService;
	
	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public VendorDTOList getVendors() {
		return new VendorDTOList(vendorService.getVendors());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO getVendor(@PathVariable("id") Long id) {
		return vendorService.getVendor(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO) {
		return vendorService.createVendor(vendorDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteVendorById(@PathVariable("id") Long id) {
		vendorService.deleteVendorById(id);
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO patchVendor(@PathVariable("id") Long id, @RequestBody VendorDTO vendorDTO) {
		return vendorService.patchVendor(id, vendorDTO);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO updateVendor(@PathVariable("id") Long id, @RequestBody VendorDTO vendorDTO) {
		return vendorService.updateVendor(id, vendorDTO);
	}
	
	
	
}
