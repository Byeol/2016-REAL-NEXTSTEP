package org.nhnnext.nextstep.enrollment;

import org.nhnnext.nextstep.course.CourseExcerpt;
import org.nhnnext.nextstep.user.UserExcerpt;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = Enrollment.class)
public interface EnrollmentExcerpt {

    Long getId();

    UserExcerpt getUser();

    CourseExcerpt getCourse();

    Enrollment.Status getStatus();
}