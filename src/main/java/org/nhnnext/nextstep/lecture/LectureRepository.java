package org.nhnnext.nextstep.lecture;

import org.nhnnext.nextstep.core.repository.AuditingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = LectureExcerpt.class)
public interface LectureRepository extends AuditingRepository<Lecture, Long> {
}
