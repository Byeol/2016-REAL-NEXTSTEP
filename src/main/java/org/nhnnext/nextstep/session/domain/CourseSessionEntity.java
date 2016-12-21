package org.nhnnext.nextstep.session.domain;

import org.nhnnext.nextstep.course.domain.CourseEntity;
import org.springframework.security.core.Authentication;

public interface CourseSessionEntity extends CourseEntity {

    boolean isParticipant(Authentication authentication);
}
