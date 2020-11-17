package com.example.snownman.api.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {
	
	 @Value("${swagger.info.title}")
	    private String title;
	    @Value("${swagger.info.description}")
	    private String description;
	    @Value("${swagger.info.version}")
	    private String version;
	    @Value("${swagger.info.termsOfServiceUrl}")
	    private String termsOfServiceUrl;
	    @Value("${swagger.info.contactName}")
	    private String contactName;
	    @Value("${swagger.info.contactUrl}")
	    private String contactUrl;
	    @Value("${swagger.info.contactEmail}")
	    private String contactEmail;
	    @Value("${swagger.info.license}")
	    private String license;
	    @Value("${swagger.info.licenseUrl}")
	    private String licenseUrl;

	    private final Contact DEFAULT_CONTACT = new Contact(contactName, contactUrl, contactEmail);
	    private final ApiInfo DEFAULT_API_INFO = new ApiInfo(title, description, version, termsOfServiceUrl,
	            DEFAULT_CONTACT, license, licenseUrl);
	    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
	            new HashSet<>(Collections.singletonList("application/json"));

	    @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(DEFAULT_API_INFO)
	                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
	                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	    }

}
