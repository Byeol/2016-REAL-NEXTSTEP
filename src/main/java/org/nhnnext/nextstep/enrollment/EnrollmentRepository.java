package org.nhnnext.nextstep.enrollment;

import org.nhnnext.nextstep.core.repository.AuditingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = EnrollmentExcerpt.class)
public interface EnrollmentRepository extends AuditingRepository<Enrollment, Long> {
}
