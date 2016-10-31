package org.nhnnext.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@Entity
public class Course extends AbstractPersistable<Long> {

	public Course() {
		this.state = State.IN_SESSION;
	}

	@NotNull
	private String name;

	private State state;

	@Lob
	private String description;

	@ManyToMany
	private Collection<User> instructors;

	@ManyToMany
	private Collection<User> participants;

	@OneToMany(mappedBy = "course")
//	@OrderColumn(name = "lecture_order")
	private List<Lecture> lectures;

	void swapLecture(int i, int j) {
		Collections.swap(lectures, i, j);
	}

	public enum State {
		UPCOMING,
		IN_SESSION
	}

}
