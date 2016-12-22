package org.nhnnext.nextstep.lesson.domain;

import org.nhnnext.nextstep.session.domain.CourseSessionEntity;

public interface LessonEntity extends CourseSessionEntity {

    boolean isPublic();
}
