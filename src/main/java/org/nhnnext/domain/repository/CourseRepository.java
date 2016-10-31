package org.nhnnext.domain.repository;

import org.nhnnext.domain.Course;
import org.nhnnext.domain.CourseExcerpt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = CourseExcerpt.class)
public interface CourseRepository extends CrudRepository<Course, Long> {
}
