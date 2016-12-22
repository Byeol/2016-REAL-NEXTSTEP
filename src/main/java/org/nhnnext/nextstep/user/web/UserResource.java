package org.nhnnext.nextstep.user.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.nhnnext.nextstep.user.User;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserResource extends ResourceSupport {

    private final User user;
    private final Collection<? extends GrantedAuthority> authorities;
}
