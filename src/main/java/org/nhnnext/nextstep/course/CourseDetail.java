package org.nhnnext.nextstep.course;

import org.nhnnext.nextstep.session.CourseSessionDetail;
import org.nhnnext.nextstep.session.CourseSessionExcerpt;
import org.nhnnext.nextstep.session.MasterSessionDetail;
import org.nhnnext.nextstep.user.UserExcerpt;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "detail", types = Course.class)
public interface CourseDetail {

    String getName();

    String getDescription();

    List<UserExcerpt> getInstructors();

    List<CourseSessionExcerpt> getCourseSessions();

    MasterSessionDetail getMasterSession();

    CourseSessionDetail getDefaultSession();
}
