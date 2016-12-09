package org.nhnnext.nextstep.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity<U, PK extends Serializable> extends AbstractAuditable<U, PK> implements AuditingEntity<U, PK> {

	@JsonIgnore
	@Transient
	public boolean isCreatedBy(U createdBy) {
		return createdBy == getCreatedBy();
	}
}
