package org.nhnnext.nextstep.course;

import org.nhnnext.nextstep.session.SessionExcerpt;
import org.nhnnext.nextstep.user.Instructor;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "excerpt", types = Course.class)
public interface CourseExcerpt {

    String getName();

    List<Instructor> getInstructors();
}
