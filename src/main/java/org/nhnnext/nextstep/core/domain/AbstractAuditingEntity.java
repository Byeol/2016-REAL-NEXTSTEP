package org.nhnnext.nextstep.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.nhnnext.nextstep.user.AuthenticationUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity<U, PK extends Serializable> extends AbstractAuditable<U, PK> implements AuditingEntity<U, PK> {

	@JsonIgnore
	@Transient
	public boolean isCreatedBy(U createdBy) {
		return getCreatedBy() != null && getCreatedBy().equals(createdBy);

	}
}
