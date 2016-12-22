package org.nhnnext.nextstep.lesson.domain;

import org.nhnnext.nextstep.session.domain.CourseSessionEntity;
import org.springframework.security.core.Authentication;

public interface LessonEntity extends CourseSessionEntity {

    boolean isPublic();
}
