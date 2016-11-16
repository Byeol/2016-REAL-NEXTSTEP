package org.nhnnext.config;

import org.nhnnext.domain.SecurityUser;
import org.nhnnext.repository.SecurityUserRepository;
import org.nhnnext.security.SpringSecurityAuditorAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

	@Autowired
	private SecurityUserRepository securityUserRepository;

	@Bean
	public AuditorAware<SecurityUser> auditorProvider() {
		return new SpringSecurityAuditorAware(securityUserRepository);
	}
}
