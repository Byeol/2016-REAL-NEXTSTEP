package org.nhnnext.nextstep.user.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.nhnnext.nextstep.user.OAuth2User;
import org.nhnnext.nextstep.user.User;
import org.nhnnext.nextstep.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@CommonsLog
@Component
public class AuthenticationEventListener {

    private final PrincipalExtractor principalExtractor = new FixedPrincipalExtractor();
    private final UserRepository userRepository;

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        if (!(event.getAuthentication() instanceof OAuth2Authentication)) {
            return;
        }

        OAuth2Authentication authentication = (OAuth2Authentication) event.getAuthentication();
        authentication.getPrincipal();

        Map<String, Object> map = (Map<String, Object>) authentication.getUserAuthentication().getDetails();

        OAuth2User user = getUser(map);
        logger.info("Authenticated user: " + user);
    }

    private OAuth2User getUser(Map<String, Object> map) {
        String username = principalExtractor.extractPrincipal(map).toString();
        User user = userRepository.findByUsername(username).orElse(new User(username));

        if (user.isNew()) {
            user.setName(map.get("name").toString());
            user.setEmail(map.get("email").toString());
            user.setAvatarUrl(map.get("avatar_url").toString());

            logger.info("Creating new user: " + username);
            userRepository.save(user);
        }

        return user;
    }
}
