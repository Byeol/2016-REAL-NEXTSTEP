package org.nhnnext.repository;

import org.nhnnext.domain.Lesson;
import org.nhnnext.domain.LessonExcerpt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = LessonExcerpt.class)
public interface LessonRepository extends CrudRepository<Lesson, Long> {
}
