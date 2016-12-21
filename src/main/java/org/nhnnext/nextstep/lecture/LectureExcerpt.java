package org.nhnnext.nextstep.lecture;

import org.nhnnext.nextstep.lesson.LessonExcerpt;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "excerpt", types = Lecture.class)
public interface LectureExcerpt {

    Long getId();

    String getName();

    List<LessonExcerpt> getLessons();

    Object getPos();
}
