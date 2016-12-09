package org.nhnnext.nextstep.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.nhnnext.nextstep.core.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Data
@Entity
public class MyUser extends AbstractPersistable<Long> {

    //    @Id
    @Column(unique = true, nullable = false)
    private final Integer authId;
//    private Long authId;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @URL
    private String avatarUrl;
}
