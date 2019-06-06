package com.ayc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ayc.api.v1.mapper.VendorMapper;
import com.ayc.api.v1.model.VendorDTO;
import com.ayc.domain.Vendor;
import com.ayc.exception.ResourceNotFoundException;
import com.ayc.repositories.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {
	
	VendorRepository vendorRepository;
	VendorMapper vendorMapper;
	
	private static final String VENDOR_URL = "/api/v1/vendors/";	
	
	public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
		this.vendorRepository = vendorRepository;
		this.vendorMapper = vendorMapper;
	}
	
	@Override
	public List<VendorDTO> getVendors() {
		return vendorRepository.findAll()
				.stream()
				.map(vendor -> {
					
					VendorDTO venderDTO = vendorMapper.vendorToVendorDTO(vendor);
					venderDTO.setVendorUrl(getVendorUrl(vendor.getId()));
					
					return venderDTO;
					
		}).collect(Collectors.toList());
	}

	@Override
	public VendorDTO getVendor(Long id) {
		return vendorRepository.findById(id).map(vendor -> {
			
			VendorDTO returnedDTO = vendorMapper.vendorToVendorDTO(vendor);
			returnedDTO.setVendorUrl(getVendorUrl(vendor.getId()));
			
			return returnedDTO;
			
		}).orElseThrow(ResourceNotFoundException::new);
				
		
	}
	
	private String getVendorUrl(Long id) {
		return VENDOR_URL + id;
	}

	
	@Override
	public VendorDTO createVendor(VendorDTO vendorDTO) {
		return saveVendor(vendorMapper.vendorDTOToVender(vendorDTO));
	}
	
	
	@Override
	public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
		return vendorRepository.findById(id).map(vendor -> {
			
			if(vendorDTO.getName() != null) {
				vendor.setName(vendorDTO.getName());
			}
			
			return saveVendor(vendor);
		}).orElseThrow(ResourceNotFoundException::new);
	}

	
	@Override
	public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
		Vendor vendor = vendorMapper.vendorDTOToVender(vendorDTO);
		vendor.setId(id);
		
		return saveVendor(vendor);
	}
	
	
	private VendorDTO saveVendor(Vendor vendor) {
		Vendor savedVendor = vendorRepository.save(vendor);
		
		VendorDTO returnedVenderDTO = vendorMapper.vendorToVendorDTO(savedVendor);
		returnedVenderDTO.setVendorUrl(getVendorUrl(savedVendor.getId()));
		return returnedVenderDTO;
	}


	@Override
	public void deleteVendorById(Long id) {
		vendorRepository.deleteById(id);
	}

}
