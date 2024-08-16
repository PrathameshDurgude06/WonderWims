package com.tour.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfigurationJWT {
	
	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String DELETE = "DELETE";
	private static final String PUT = "PUT";
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
		configuration.setAllowedMethods(Arrays.asList(GET,POST,DELETE,PUT));
		configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type","Access-Control-Allow-Origin"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
//
//	public WebMvcConfigurer corsConfigurer()
//	{
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//			
//				registry.addMapping("/**")
//				.allowedMethods(GET,PUT,POST,DELETE)
//				.allowedHeaders("*")
//				.allowedOriginPatterns("*")
//				.allowedOrigins("http://localhost:5173")
//				.allowCredentials(true)
//				;
//			}
//		};
//	}
}