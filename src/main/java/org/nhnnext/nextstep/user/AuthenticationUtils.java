package org.nhnnext.nextstep.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthenticationUtils {

    public static User getUser(Authentication authentication) {
        if (authentication == null) {
            return null;
        }

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(GrantedAuthorities.ROLE_USER))) {
            return new User(authentication.getName());
        }

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(GrantedAuthorities.ROLE_INSTRUCTOR))) {
            return new Instructor(authentication.getName());
        }

        return null;
    }
}
