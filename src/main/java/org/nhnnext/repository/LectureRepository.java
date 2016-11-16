package org.nhnnext.repository;

import org.nhnnext.domain.Lecture;
import org.nhnnext.domain.LectureExcerpt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = LectureExcerpt.class)
public interface LectureRepository extends CrudRepository<Lecture, Long> {
}
