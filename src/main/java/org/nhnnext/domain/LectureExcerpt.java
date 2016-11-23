package org.nhnnext.domain;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "excerpt", types = Lecture.class)
public interface LectureExcerpt {

	String getTitle();

	List<Lesson> getIssues();
}
