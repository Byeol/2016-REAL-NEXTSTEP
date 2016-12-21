package org.nhnnext.nextstep.session;

import org.nhnnext.nextstep.core.repository.AuditingRepository;

//@RepositoryRestResource(excerptProjection = SessionExcerpt.class)
public interface MySessionRepository extends AuditingRepository<Session, Long> {
}
