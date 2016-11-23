package org.nhnnext.repository;

import org.nhnnext.domain.Course;
import org.nhnnext.domain.CourseExcerpt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(excerptProjection = CourseExcerpt.class)
//@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
public interface CourseRepository extends CrudRepository<Course, Long> {

	@Override
//	@PreAuthorize("#course?.instructor == null or #course?.instructor?.name == authentication?.name")
//	@PreAuthorize("hasPermission(#course, 'write')")
	Course save(@Param("course") Course course);

	@Override
//	@PreAuthorize("@courseRepository.findOne(#id)?.instructor?.name == authentication?.name")
	void delete(@Param("id") Long id);

	@Override
//	@PreAuthorize("#course?.instructor?.name == authentication?.name")
	void delete(@Param("course") Course course);
}
