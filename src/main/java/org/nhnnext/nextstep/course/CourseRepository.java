package org.nhnnext.nextstep.course;

import org.nhnnext.nextstep.core.repository.AuditingRepository;
import org.nhnnext.nextstep.course.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends AuditingRepository<Course, Long> {
}
