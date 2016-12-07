package org.nhnnext.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.nhnnext.domain.auditing.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Id;

public interface OAuth2User {

    Integer getAuthId();
}
