package com.ayc.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class VendorDTO {
	private Long id;
	
	@ApiModelProperty(value = "Name of a Vendor")
	private String name;
	@JsonProperty("customer_url")
	private String vendorUrl;

}
