package org.nhnnext.config;

import org.nhnnext.domain.SecurityUser;
import org.nhnnext.security.SpringDataJpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SpringDataJpaUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		auth
				.userDetailsService(this.userDetailsService)
					.passwordEncoder(SecurityUser.PASSWORD_ENCODER);
		// @formatter:on
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
				.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/api/**").permitAll()
					.antMatchers(HttpMethod.POST, "/api/**").authenticated()
					.antMatchers(HttpMethod.PATCH, "/api/**").authenticated()
					.antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
					.anyRequest().permitAll()
					.and()
				.csrf()
					.disable()
				.headers()
					.frameOptions().disable()
					.and()
				.httpBasic();
//					.and()
//				.formLogin();
		// @formatter:on
	}
}
