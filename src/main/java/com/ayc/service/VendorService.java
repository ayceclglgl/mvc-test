package com.ayc.service;

import java.util.List;

import com.ayc.api.v1.model.VendorDTO;

public interface VendorService {

	List<VendorDTO> getVendors();

	VendorDTO createVendor(VendorDTO vendorDTO);

	void deleteVendorById(Long id);

	VendorDTO getVendor(Long id);

	VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

	VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

}
