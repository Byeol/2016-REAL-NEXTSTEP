package org.nhnnext.nextstep.core;

import org.nhnnext.nextstep.user.SecurityUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.concurrent.Callable;

public class SecurityUtils {

    public static Authentication withAnonymousUser() {
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS");
        return new AnonymousAuthenticationToken("key", "anonymous", authorities);
    }

    public static Authentication withMockUser(SecurityUser user) {
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    private static void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private static void clearContext() {
        SecurityContextHolder.clearContext();
    }

    public static void runAs(Authentication authentication, Runnable runnable) {
        setAuthentication(authentication);
        runnable.run();
        clearContext();
    }

    public static Object runAs(Authentication authentication, Callable callable) throws Exception {
        setAuthentication(authentication);
        Object ret = callable.call();
        clearContext();
        return ret;
    }

}