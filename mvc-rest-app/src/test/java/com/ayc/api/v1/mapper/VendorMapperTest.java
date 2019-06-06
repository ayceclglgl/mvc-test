package com.ayc.api.v1.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ayc.api.v1.model.VendorDTO;
import com.ayc.domain.Vendor;

public class VendorMapperTest {
	VendorMapper mapper = VendorMapper.INSTANCE;

	private static long id = 1L;
	private static String NAME = "name";
	
	@Test
	public void vendorToVendorDTOTest() {
		Vendor vendor = new Vendor();
		vendor.setId(id);
		vendor.setName(NAME);
		
		VendorDTO vendorDTO = mapper.vendorToVendorDTO(vendor);
		
		assertNotNull(vendorDTO);
		assertEquals(Long.valueOf(id), vendorDTO.getId());
		assertEquals(NAME, vendorDTO.getName());
	}
	
	@Test
	public void vendorDTOToVendorTest() {
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setId(id);
		vendorDTO.setName(NAME);
		
		Vendor vendor = mapper.vendorDTOToVender(vendorDTO);
		
		assertNotNull(vendor);
		assertEquals(Long.valueOf(id), vendor.getId());
		assertEquals(NAME, vendor.getName());
	}

}
