package org.nhnnext.nextstep.session;

import org.nhnnext.nextstep.core.repository.AuditingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = SessionExcerpt.class)
public interface MySessionRepository extends AuditingRepository<Session, Long> {
}
