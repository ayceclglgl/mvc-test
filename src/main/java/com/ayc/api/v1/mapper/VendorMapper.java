package com.ayc.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ayc.api.v1.model.VendorDTO;
import com.ayc.domain.Vendor;

@Mapper
public interface VendorMapper {
	VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
	
	VendorDTO vendorToVendorDTO(Vendor vendor);
	Vendor vendorDTOToVender(VendorDTO vendorDTO);
	
}
