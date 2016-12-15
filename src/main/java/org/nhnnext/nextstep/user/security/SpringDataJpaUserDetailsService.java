package org.nhnnext.nextstep.user.security;

import lombok.RequiredArgsConstructor;
import org.nhnnext.nextstep.user.SecurityUser;
import org.nhnnext.nextstep.user.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

	private final UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecurityUser securityUser = this.repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("Username {0} not found", username)));

		return new User(securityUser.getUsername(), securityUser.getPassword(), securityUser.getAuthorities());
	}
}
