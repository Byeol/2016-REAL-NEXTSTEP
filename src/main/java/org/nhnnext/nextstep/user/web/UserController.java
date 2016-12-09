package org.nhnnext.nextstep.user.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/api/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
