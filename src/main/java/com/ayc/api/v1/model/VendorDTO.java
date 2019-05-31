package com.ayc.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class VendorDTO {
	private Long id;
	private String name;
	@JsonProperty("customer_url")
	private String vendorUrl;

}
