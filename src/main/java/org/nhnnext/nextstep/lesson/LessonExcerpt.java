package org.nhnnext.nextstep.lesson;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = Lesson.class)
public interface LessonExcerpt {

    Long getId();

    String getName();
}