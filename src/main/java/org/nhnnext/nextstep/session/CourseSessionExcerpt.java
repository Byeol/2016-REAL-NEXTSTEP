package org.nhnnext.nextstep.session;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = CourseSession.class)
public interface CourseSessionExcerpt {

    Long getId();

    String getName();

    String getDescription();
}
