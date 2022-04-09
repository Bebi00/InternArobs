package com.example.musify;

import com.example.musify.security.JWTUtils;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MusifyApplication {
	private final Logger log=LoggerFactory.getLogger(MusifyApplication.class);

	private JWTUtils jwtUtils;

	@Value("${spring.datasource.url}")
	private String sqlURL;

	@Autowired
	public MusifyApplication(JWTUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}


	public static void main(String[] args) {

		SpringApplication.run(MusifyApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		final String securitySchemeName = "Bearer Authentication";
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(
						new Components()
								.addSecuritySchemes(securitySchemeName,
										new SecurityScheme()
												.name(securitySchemeName)
												.type(SecurityScheme.Type.HTTP)
												.scheme("bearer")
												.bearerFormat("JWT")
								)
				);
	}





}
