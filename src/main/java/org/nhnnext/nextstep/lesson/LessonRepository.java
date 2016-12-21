package org.nhnnext.nextstep.lesson;

import org.nhnnext.nextstep.core.repository.AuditingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = LessonExcerpt.class)
public interface LessonRepository extends AuditingRepository<Lesson, Long> {
}