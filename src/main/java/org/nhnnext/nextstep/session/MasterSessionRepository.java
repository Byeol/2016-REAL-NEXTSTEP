package org.nhnnext.nextstep.session;

import org.nhnnext.nextstep.core.repository.AuditingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false, excerptProjection = SessionExcerpt.class)
public interface MasterSessionRepository extends AuditingRepository<MasterSession, Long> {
}
