package org.nhnnext.domain;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "excerpt", types = Lesson.class)
public interface LessonExcerpt {

	String getTitle();

	String getContent();
}
