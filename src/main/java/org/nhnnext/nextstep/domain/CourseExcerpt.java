package org.nhnnext.nextstep.domain;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;

@Projection(name = "excerpt", types = Course.class)
public interface CourseExcerpt {

	String getName();

	Collection<User> getInstructors();

//	List<Lecture> getLectures();
}
