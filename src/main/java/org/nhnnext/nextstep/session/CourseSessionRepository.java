package org.nhnnext.nextstep.session;

import org.nhnnext.nextstep.core.repository.AuditingRepository;

//@RepositoryRestResource(exported = false, excerptProjection = CourseSessionExcerpt.class)
public interface CourseSessionRepository extends AuditingRepository<CourseSession, Long> {
}
