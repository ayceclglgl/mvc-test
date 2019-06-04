package com.ayc.configuration;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration { //} extends WebMvcConfigurationSupport { // Use when project is not Spring Boot
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.build()
				.pathMapping("/")
				.apiInfo(metaData());
				
	}

	private ApiInfo metaData() {
		Contact contact = new Contact("Ayc", "http://localhost:8080/api/v1/customers",
				"email");
		
		 return new ApiInfo(
	                "Ayc Title",
	                "Ayc Description",
	                "1.0",
	                "Terms of Service: blah",
	                contact,
	                "Apache License Version 2.0",
	                "https://www.apache.org/licenses/LICENSE-2.0",
	                new ArrayList<>());
	}
	
//  Use when project is not Spring Boot
//  @Override
//  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//      registry.addResourceHandler("swagger-ui.html")
//              .addResourceLocations("classpath:/META-INF/resources/");
//
//      registry.addResourceHandler("/webjars/**")
//              .addResourceLocations("classpath:/META-INF/resources/webjars/");
//  }

}
