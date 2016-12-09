//package org.nhnnext.nextstep.config;
//
//import org.nhnnext.nextstep.domain.SecurityUser;
//import org.nhnnext.nextstep.repository.old.SecurityUserRepository;
//import org.nhnnext.nextstep.security.SpringSecurityAuditorAware;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//
//@Configuration
//@EnableJpaAuditing
//public class AuditingConfig {
//
//	@Autowired
//	private SecurityUserRepository securityUserRepository;
//
//	@Bean
//	public AuditorAware<SecurityUser> auditorProvider() {
//		return new SpringSecurityAuditorAware(securityUserRepository);
//	}
//}
