package org.nhnnext.nextstep.course.domain;

import org.springframework.security.core.Authentication;

public interface CourseEntity {

    boolean isInstructor(Authentication authentication);
}
