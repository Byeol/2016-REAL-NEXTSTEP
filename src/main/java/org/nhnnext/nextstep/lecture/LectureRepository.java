package org.nhnnext.nextstep.lecture;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = LectureExcerpt.class)
public interface LectureRepository extends CrudRepository<Lecture, Long> {
}
