//package org.nhnnext.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//
//@Configuration
////@EnableOAuth2Sso
//@EnableWebSecurity
////@EnableOAuth2Client
////@EnableEmbeddedRedis
////@EnableRedisHttpSession
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
////	@Autowired
////	private SpringDataJpaUserDetailsService userDetailsService;
//
////	@Resource(name = "customUserDetailsService")
////	private UserDetailsService customUserDetailsService;
////
////	@Autowired
////	OAuth2ClientContext oauth2ClientContext;
//
////	@Autowired
////	private ResourceServerProperties sso;
//
////	@Override
////	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		// @formatter:off
////		auth.userDetailsService(this.userDetailsService).passwordEncoder(SecurityUser.PASSWORD_ENCODER);
////		// @formatter:on
////	}
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////    	auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
////    }
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// @formatter:off
//		http
//			.authorizeRequests()
////			.antMatchers("/login**").permitAll()
//			.antMatchers(HttpMethod.GET, "/api/**").permitAll()
//			.antMatchers(HttpMethod.POST, "/api/**").permitAll()
//			.antMatchers(HttpMethod.PATCH, "/api/**").permitAll()
//			.antMatchers(HttpMethod.DELETE, "/api/**").permitAll()
////			.antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
//			.anyRequest().permitAll()
//			.and().logout().logoutSuccessUrl("/").permitAll()
//			.and().csrf().disable()
//			.headers().frameOptions().disable()
//			.and()
//				.httpBasic();
////			.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
//		// .and()
//		// .formLogin();
//		// @formatter:on
//	}
//
////	@Bean
////	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
////		FilterRegistrationBean registration = new FilterRegistrationBean();
////		registration.setFilter(filter);
////		registration.setOrder(-100);
////		return registration;
////	}
//
////	@Primary
////    @Bean
////	public ResourceServerTokenServices userInfoTokenServices() {
////		return new CustomResourceServerTokenServices(sso.getUserInfoUri(), sso.getClientId());
////	}
//
////	@Bean
////	@ConfigurationProperties("github.client")
////	public AuthorizationCodeResourceDetails github() {
////		return new AuthorizationCodeResourceDetails();
////	}
////
////	@Primary
////	@Bean
////	@ConfigurationProperties("github.resource")
////	public ResourceServerProperties githubResource() {
////		return new ResourceServerProperties();
////	}
////
////	private Filter ssoFilter() {
////
////		OAuth2ClientAuthenticationProcessingFilter githubFilter = new OAuth2ClientAuthenticationProcessingFilter(
////				"/login/github");
////		OAuth2RestTemplate githubTemplate = new OAuth2RestTemplate(github(), oauth2ClientContext);
////		githubFilter.setRestTemplate(githubTemplate);
////		githubFilter
////				.setTokenServices(new UserInfoTokenServices(githubResource().getUserInfoUri(), github().getClientId()));
////		return githubFilter;
////	}
////
////    @Bean
////	public PasswordEncoder passwordEncoder() {
////		return new BCryptPasswordEncoder();
////	}
//
//}
