package org.nhnnext.nextstep.lesson;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = LessonExcerpt.class)
public interface LessonRepository extends CrudRepository<Lesson, Long> {
}