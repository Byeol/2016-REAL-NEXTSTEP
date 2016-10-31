package org.nhnnext.domain;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.List;

@Projection(name = "excerpt", types = Course.class)
public interface CourseExcerpt {

	String getName();

	Collection<User> getInstructors();

//	List<Lecture> getLectures();
}
