package org.nhnnext.nextstep.user.security;

import lombok.RequiredArgsConstructor;
import org.nhnnext.nextstep.user.User;
import org.nhnnext.nextstep.user.UserRepository;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class SpringSecurityAuditorAware implements AuditorAware<User> {

	private final UserRepository repository;

	@Override
	public User getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		return this.repository.findByUsername(authentication.getName()).orElse(null);
	}
}
