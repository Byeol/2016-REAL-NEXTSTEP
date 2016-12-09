//package org.nhnnext.nextstep.security;
//
//import lombok.RequiredArgsConstructor;
//import org.nhnnext.nextstep.domain.SecurityUser;
//import org.nhnnext.nextstep.repository.old.SecurityUserRepository;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@RequiredArgsConstructor
//@Component
//public class SpringDataJpaUserDetailsService implements UserDetailsService {
//
//	private final SecurityUserRepository repository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		SecurityUser securityUser = this.repository.findByName(username);
//
//		return new User(securityUser.getName(), securityUser.getPassword(),
//				AuthorityUtils.createAuthorityList(securityUser.getRoles()));
//	}
//
//}
