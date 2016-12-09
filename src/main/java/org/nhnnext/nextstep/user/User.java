package org.nhnnext.nextstep.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.nhnnext.nextstep.core.AbstractEntity;

import javax.persistence.*;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("USER")
public class User extends AbstractEntity implements OAuth2User {

    //    @Id
    @Column(unique = true, nullable = false)
    private final String username;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @URL
    private String avatarUrl;
}
