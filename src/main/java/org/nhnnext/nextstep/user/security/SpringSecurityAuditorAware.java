package org.nhnnext.nextstep.user.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.nhnnext.nextstep.user.User;
import org.nhnnext.nextstep.user.UserRepository;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
@CommonsLog
public class SpringSecurityAuditorAware implements AuditorAware<User> {

	private final UserRepository repository;

	@Override
	public User getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		logger.info("Current Auditor: " + authentication.getName());

		return this.repository.findByUsername(authentication.getName()).orElse(null);
	}
}
