package org.nhnnext.nextstep.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.nhnnext.nextstep.core.domain.AbstractEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("USER")
public class User extends AbstractEntity implements OAuth2User, SecurityUser {

    //    @Id
    @Column(unique = true, nullable = false)
    private final String username;

//    @Embedded
//    private final Set<GrantedAuthority> authorities = new HashSet<>();

    @NotEmpty
    private String name;

    @Email
    private String email;

    @URL
    private String avatarUrl;

    private String role = "USER";

    @JsonIgnore
    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(role);
    }

    @JsonIgnore
    @Transient
    public String getPassword() {
        return null;
    }
}
