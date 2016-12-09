//package org.nhnnext.nextstep.repository.old;
//
//import org.nhnnext.nextstep.domain.Course;
//import org.nhnnext.nextstep.domain.CourseExcerpt;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//@RepositoryRestResource(excerptProjection = CourseExcerpt.class)
////@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
//public interface CourseRepository extends CrudRepository<Course, Long> {
//
//	@Override
////	@PreAuthorize("#course?.instructor == null or #course?.instructor?.name == authentication?.name")
////	@PreAuthorize("hasPermission(#course, 'write')")
//	Course save(@Param("course") Course course);
//
//	@Override
////	@PreAuthorize("@courseRepository.findOne(#id)?.instructor?.name == authentication?.name")
//	void delete(@Param("id") Long id);
//
//	@Override
////	@PreAuthorize("#course?.instructor?.name == authentication?.name")
//	void delete(@Param("course") Course course);
//}
