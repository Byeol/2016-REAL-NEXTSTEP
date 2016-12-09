package org.nhnnext.nextstep.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private SpringDataJpaUserDetailsService userDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// @formatter:off
//		auth
//				.userDetailsService(this.userDetailsService)
//					.passwordEncoder(SecurityUser.PASSWORD_ENCODER);
//		// @formatter:on
//	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// @formatter:off
//		http
//				.authorizeRequests()
////					.antMatchers(HttpMethod.GET, "/api/**").permitAll()
////					.antMatchers(HttpMethod.POST, "/api/**").authenticated()
////					.antMatchers(HttpMethod.PATCH, "/api/**").authenticated()
////					.antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
//					.anyRequest().permitAll()
//					.and()
//				.csrf()
//					.disable()
//				.headers()
//					.frameOptions().disable()
//					.and()
//				.httpBasic();
////					.and()
////				.formLogin();
//		// @formatter:on
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
        http
                .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/").permitAll()
                    .and()
                .csrf()
                    .disable()
                .headers()
                    .frameOptions().disable();
        // @formatter:on
	}

}
