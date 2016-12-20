package org.nhnnext.nextstep.course;

import org.nhnnext.nextstep.session.*;
import org.nhnnext.nextstep.user.Instructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "detail", types = Course.class)
public interface CourseDetail {

    String getName();

    String getDescription();

    List<Instructor> getInstructors();

    List<SessionExcerpt> getSessions();

    SessionDetail getMasterSession();

    SessionDetail getDefaultSession();
}
