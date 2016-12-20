package org.nhnnext.nextstep.course;

import org.nhnnext.nextstep.core.repository.AuditingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = CourseExcerpt.class)
public interface CourseRepository extends AuditingRepository<Course, Long> {
}
