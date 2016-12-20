package org.nhnnext.nextstep.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(of = "username")
@ToString(of = "username")
@Entity
@Inheritance // (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("USER")
public class User extends AbstractSecurityUser implements OAuth2User {

    //    @Id
    @Column(unique = true, nullable = false)
    private final String username;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @URL
    private String avatarUrl;

//    @JsonIgnore
    @Transient
    public String getRole() {
        return GrantedAuthorities.ROLE_USER;
    }
}
