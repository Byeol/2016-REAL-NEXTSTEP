package org.nhnnext.nextstep.enrollment;

import org.nhnnext.nextstep.session.CourseSessionExcerpt;
import org.nhnnext.nextstep.user.UserExcerpt;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = Enrollment.class)
public interface EnrollmentExcerpt {

    Long getId();

    UserExcerpt getUser();

    CourseSessionExcerpt getSession();

    Enrollment.Status getStatus();
}