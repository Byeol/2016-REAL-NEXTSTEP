package org.nhnnext.security;

import lombok.RequiredArgsConstructor;
import org.nhnnext.domain.SecurityUser;
import org.nhnnext.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class SpringSecurityAuditorAware implements AuditorAware<SecurityUser> {

	private final SecurityUserRepository repository;

	@Override
	public SecurityUser getCurrentAuditor() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		return this.repository.findByName(authentication.getName());
	}
}
