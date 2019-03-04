package com.samplespring.rest.webservices.restfulwebservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import springfox.documentation.swagger2.annotations;
import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//Configuration
@Configuration
//Enable Swagger
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2);
	}
	
	//Bean - Docket
		//Swagger 2
		//All the paths
		//All the APIs
	
}
