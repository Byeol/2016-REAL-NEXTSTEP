//package org.nhnnext.nextstep.security;
//
//import lombok.RequiredArgsConstructor;
//import org.nhnnext.nextstep.domain.SecurityUser;
//import org.nhnnext.nextstep.repository.old.SecurityUserRepository;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//@RequiredArgsConstructor
//public class SpringSecurityAuditorAware implements AuditorAware<SecurityUser> {
//
//	private final SecurityUserRepository repository;
//
//	@Override
//	public SecurityUser getCurrentAuditor() {
//
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		if (authentication == null || !authentication.isAuthenticated()) {
//			return null;
//		}
//
//		return this.repository.findByName(authentication.getName());
//	}
//}
