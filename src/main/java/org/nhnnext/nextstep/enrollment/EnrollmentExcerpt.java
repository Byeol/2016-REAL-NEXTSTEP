package org.nhnnext.nextstep.enrollment;

import org.nhnnext.nextstep.user.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = Enrollment.class)
public interface EnrollmentExcerpt {

    User getUser();

    Enrollment.Status getStatus();
}