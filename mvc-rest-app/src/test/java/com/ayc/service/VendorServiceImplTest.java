package com.ayc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ayc.api.v1.mapper.VendorMapper;
import com.ayc.api.v1.model.VendorDTO;
import com.ayc.domain.Vendor;
import com.ayc.repositories.VendorRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class VendorServiceImplTest {
	
	@Mock
	VendorRepository repository;
	
	VendorService service;

	private static long id = 1L;
	private static String NAME = "Name";
	private static final String VENDOR_URL = "/api/v1/vendors/";
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new VendorServiceImpl(repository, VendorMapper.INSTANCE);
	}
	
	@Test
	public void testGetVendors() {
		when(repository.findAll()).thenReturn(Arrays.asList(new Vendor(), new Vendor()));
		
		List<VendorDTO> list = service.getVendors();
		assertNotNull(list);
		assertEquals(2, list.size());
	}
	
	@Test
	public void testGetVendor(){
		Vendor vendor = new Vendor();
		vendor.setId(id);
		vendor.setName(NAME);
		
		when(repository.findById(anyLong())).thenReturn(Optional.of(vendor));
		
		VendorDTO vendorDTO = service.getVendor(id);
		assertNotNull(vendorDTO);
		assertEquals(Long.valueOf(id), vendorDTO.getId());
		assertEquals(NAME, vendorDTO.getName());
		assertEquals(VENDOR_URL + vendorDTO.getId(), vendorDTO.getVendorUrl());
		
	}

	@Test
	public void testCreateVendor() {
		Vendor vendor = new Vendor();
		vendor.setId(id);
		vendor.setName(NAME);
		when(repository.save(any())).thenReturn(vendor);
		
		VendorDTO returnedDTO = service.createVendor(new VendorDTO());
		
		assertNotNull(returnedDTO);
		assertEquals(Long.valueOf(id), returnedDTO.getId());
		assertEquals(NAME, returnedDTO.getName());
		assertEquals(VENDOR_URL + returnedDTO.getId(), returnedDTO.getVendorUrl());
	}

	@Test
	public void testDeleteVendorById(){
		service.deleteVendorById(id);
		verify(repository).deleteById(id);
	}

	@Test
	public void testPatchVendor(){
		String updatedName = "updatedName";
		Vendor updatedVendor= new Vendor();
		updatedVendor.setName(updatedName);
		
		Vendor vendor = new Vendor();
		vendor.setId(id);
		vendor.setName(NAME);

		when(repository.findById(anyLong())).thenReturn(Optional.of(vendor));
		when(repository.save(any())).thenReturn(updatedVendor);
		
		
		VendorDTO returnedDTO = service.patchVendor(id, new VendorDTO());
		
		
		assertNotNull(returnedDTO);
		assertEquals(updatedName, returnedDTO.getName());
		assertEquals(VENDOR_URL + returnedDTO.getId(), returnedDTO.getVendorUrl());
	}

	@Test
	public void testUpdateVendor(){
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setId(id);
		vendorDTO.setName(NAME);
		
		Vendor vendor = new Vendor();
		vendor.setId(id);
		vendor.setName(NAME);
		
		when(repository.save(any())).thenReturn(vendor);
		
		
		VendorDTO returnedDTO = service.updateVendor(id, vendorDTO);
		assertNotNull(returnedDTO);
		assertEquals(NAME, returnedDTO.getName());
		assertEquals(VENDOR_URL + returnedDTO.getId(), returnedDTO.getVendorUrl());
	}
}
