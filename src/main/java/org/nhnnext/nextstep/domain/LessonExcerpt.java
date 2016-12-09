package org.nhnnext.nextstep.domain;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = Lesson.class)
public interface LessonExcerpt {

	String getTitle();

	String getContent();
}
