package com.ayc.controller.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ayc.api.v1.model.VendorDTO;
import com.ayc.service.VendorService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest extends AbstractRestController{
	
	@Autowired
	MockMvc mvc;
	
	@MockBean //provided by Spring Context
	VendorService service;
	
	private static final Long ID = 1L; 
	private static final String NAME = "name";
	
	
	@Test
	public void testGetVendors() throws Exception {
		List<VendorDTO> vendorListDTO = Arrays.asList(new VendorDTO(), new VendorDTO());
		
		given(service.getVendors()).willReturn(vendorListDTO);
		
		mvc.perform(get(VendorController.BASE_URL))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.vendors", hasSize(2))); 
	}
	
	@Test
	public void testGetVendorById() throws Exception {
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setId(ID);
		vendorDTO.setName(NAME);
		
		given(service.getVendor(anyLong())).willReturn(vendorDTO);
		
		mvc.perform(get(VendorController.BASE_URL + "/" + ID))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(NAME)));
	}
	
	@Test
	public void testCreateVendor() throws Exception {
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setId(ID);
		vendorDTO.setName(NAME);
		
		given(service.createVendor(vendorDTO)).willReturn(vendorDTO);
		
		mvc.perform(post(VendorController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(vendorDTO)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.name", equalTo(NAME)));
	}
	
	@Test
	public void testPatchVendors() throws Exception {
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setId(ID);
		vendorDTO.setName(NAME);
		
		given(service.patchVendor(anyLong(), any())).willReturn(vendorDTO);
		
		mvc.perform(patch(VendorController.BASE_URL + "/"+ ID ) 
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(vendorDTO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", equalTo(NAME)));
		
	}
	

	@Test
	public void testUpdateVendors() throws Exception {
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setId(ID);
		vendorDTO.setName(NAME);
		
		given(service.updateVendor(anyLong(), any())).willReturn(vendorDTO);
		
		mvc.perform(put(VendorController.BASE_URL + "/" + ID ) 
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(vendorDTO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", equalTo(NAME)));
	}
	
	
	
	@Test
	public void testDeleteVendor() throws Exception {
		mvc.perform(delete(VendorController.BASE_URL + "/" + ID ))
		.andExpect(status().isOk());
		
		then(service).should().deleteVendorById(anyLong());
		
	}

}
