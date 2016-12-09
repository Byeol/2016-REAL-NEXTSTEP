//package org.nhnnext.nextstep.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
//
//@Configuration
//public class WebConfig {
//
//	@Bean
//	public RoleHierarchy roleHierarchy() {
//		final RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_INSTRUCTOR");
//		roleHierarchy.setHierarchy("ROLE_INSTRUCTOR > ROLE_USER");
//		roleHierarchy.setHierarchy("ROLE_USER > ROLE_GUEST");
//		return roleHierarchy;
//	}
//
////	@Bean
////	public WebMvcConfigurer corsConfigurer() {
////		return new WebMvcConfigurerAdapter() {
////			@Override
////			public void addCorsMappings(CorsRegistry registry) {
////				registry.addMapping("/**")
////						.allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
////			}
////		};
////	}
//
////	@Bean
////	public WebMvcConfigurer corsConfigurer() {
////		return new WebMvcConfigurerAdapter() {
////			@Override
////			public void addCorsMappings(CorsRegistry registry) {
////				registry.addMapping("/**");
////			}
////		};
////	}
//
////	@Bean
////	public CorsFilter corsFilter() {
////
////		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////		CorsConfiguration config = new CorsConfiguration();
////		config.setAllowCredentials(true); // you USUALLY want this
////		config.addAllowedOrigin("*");
////		config.addAllowedHeader("*");
////		config.addAllowedMethod("GET");
////		config.addAllowedMethod("PUT");
////		source.registerCorsConfiguration("/**", config);
////		return new CorsFilter(source);
////	}
//
//}
