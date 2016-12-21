package org.nhnnext.nextstep.session;

import org.nhnnext.nextstep.core.repository.AuditingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(exported = false, excerptProjection = CourseSessionExcerpt.class)
public interface CourseSessionRepository extends AuditingRepository<CourseSession, Long> {
}
