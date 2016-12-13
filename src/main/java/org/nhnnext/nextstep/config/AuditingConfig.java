package org.nhnnext.nextstep.config;
import org.nhnnext.nextstep.user.User;
import org.nhnnext.nextstep.user.UserRepository;
import org.nhnnext.nextstep.user.security.SpringSecurityAuditorAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

	@Autowired
	private UserRepository userRepository;

	@Bean
	public AuditorAware<User> auditorProvider() {
		return new SpringSecurityAuditorAware(userRepository);
	}
}
