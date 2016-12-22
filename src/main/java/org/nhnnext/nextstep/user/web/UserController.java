package org.nhnnext.nextstep.user.web;

import lombok.RequiredArgsConstructor;
import org.nhnnext.nextstep.user.User;
import org.nhnnext.nextstep.user.UserService;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/user")
@ExposesResourceFor(User.class)
public class UserController {

    private final UserService service;
    private final EntityLinks entityLinks;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<?> getAuthenticatedUser(Authentication authentication) {
        User user = service.getAuthenticatedUser().orElseGet(null);
        UserResource resource = new UserResource(user.getId(), user, authentication.getAuthorities());
        resource.add(entityLinks.linkToSingleResource(user));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
