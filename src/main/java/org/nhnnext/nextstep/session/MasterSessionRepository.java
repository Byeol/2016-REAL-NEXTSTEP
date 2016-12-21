package org.nhnnext.nextstep.session;

import org.nhnnext.nextstep.core.repository.AuditingRepository;

//@RepositoryRestResource(exported = false, excerptProjection = SessionExcerpt.class)
public interface MasterSessionRepository extends AuditingRepository<MasterSession, Long> {
}
