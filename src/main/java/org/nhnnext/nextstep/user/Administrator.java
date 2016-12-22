package org.nhnnext.nextstep.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("ADMIN")
public class Administrator extends User {

    public Administrator(String username) {
        super(username);
    }

    @Transient
    public String getRole() {
        return GrantedAuthorities.ROLE_ADMIN;
    }
}
