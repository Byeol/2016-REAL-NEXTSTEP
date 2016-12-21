package org.nhnnext.nextstep.user.web;

import lombok.RequiredArgsConstructor;
import org.nhnnext.nextstep.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/user")
    public Map<String, Object> getAuthenticatedUser(Authentication authentication) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", service.getAuthenticatedUser().orElseGet(null));
        map.put("authorities", authentication.getAuthorities());
        return map;
    }

//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/api/user")
//    public Principal user(Principal principal) {
//        return principal;
//    }
}
