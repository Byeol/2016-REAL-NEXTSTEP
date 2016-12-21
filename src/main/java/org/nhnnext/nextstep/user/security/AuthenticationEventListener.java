package org.nhnnext.nextstep.user.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.nhnnext.nextstep.user.Instructor;
import org.nhnnext.nextstep.user.User;
import org.nhnnext.nextstep.user.UserRepository;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@CommonsLog
@Component
public class AuthenticationEventListener {

    private final PrincipalExtractor principalExtractor = new FixedPrincipalExtractor();
    private final UserRepository userRepository;

    private final SpringDataJpaUserDetailsService userDetailsService;

    @EventListener
    public void handleAuthenticationSuccess(InteractiveAuthenticationSuccessEvent event) {
        if (!(event.getAuthentication() instanceof OAuth2Authentication)) {
            return;
        }

        OAuth2Authentication authentication = (OAuth2Authentication) event.getAuthentication();
        authentication.getPrincipal();

        Map<String, Object> map = (Map<String, Object>) authentication.getUserAuthentication().getDetails();

        UserDetails user = getUser(map);

        if (logger.isInfoEnabled()) {
            logger.info("Authenticated user: " + user);
        }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
    }

    private UserDetails getUser(Map<String, Object> map) {
        String username = principalExtractor.extractPrincipal(map).toString();
//        User user = userRepository.findByUsername(username).orElse(new User(username));

        UserDetails user;

        try {
            user = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            User newUser = new User(username);

            if (username.equals("Byeol")) {
                newUser = new Instructor(username);
            }

            newUser.setName(map.get("name").toString());
            newUser.setEmail(map.get("email").toString());
            newUser.setAvatarUrl(map.get("avatar_url").toString());

            logger.info("Creating new user: " + username);
            userRepository.save(newUser);

            user = userDetailsService.loadUserByUsername(username);
        }

//        if (user.isNew()) {
//            if (username.equals("Byeol")) {
//                user = new Instructor(username);
//            }
//
//            user.setName(map.get("name").toString());
//            user.setEmail(map.get("email").toString());
//            user.setAvatarUrl(map.get("avatar_url").toString());
//
//            logger.info("Creating new user: " + username);
//            userRepository.save(user);
//        }

        return user;
    }
}
