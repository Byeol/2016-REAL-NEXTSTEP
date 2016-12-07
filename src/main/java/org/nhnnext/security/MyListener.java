package org.nhnnext.security;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.java.Log;
import org.nhnnext.domain.MyUser;
import org.nhnnext.domain.OAuth2User;
import org.nhnnext.domain.User;
import org.nhnnext.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@CommonsLog
@Component
public class MyListener {

    @Autowired
    private MyUserRepository userRepository;

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        if (!(event.getAuthentication() instanceof OAuth2Authentication)) {
            return;
        }

        OAuth2Authentication authentication = (OAuth2Authentication) event.getAuthentication();
        authentication.getPrincipal();

        Map<String, Object> map = (Map<String, Object>) authentication.getUserAuthentication().getDetails();

        MyUser user = getUser(map);
        logger.info("Authenticated user: " + user);
    }

    private MyUser getUser(Map<String, Object> map) {
        Integer id = (Integer) map.get("id");
        MyUser user = userRepository.findByAuthId(id).orElse(new MyUser(id));

        if (user.isNew()) {
            user.setName(map.get("name").toString());
            user.setEmail(map.get("email").toString());
            user.setAvatarUrl(map.get("avatar_url").toString());

            logger.info("Creating new user: " + id);
            userRepository.save(user);
        }

        return user;
    }
}
