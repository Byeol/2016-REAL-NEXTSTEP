package org.nhnnext.nextstep.domain;

import lombok.Data;
import org.nhnnext.nextstep.core.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Session extends AbstractPersistable<Long> {

	private String name;

	private State state;

	public enum State {
		UPCOMING,
		IN_SESSION
	}

	@ManyToOne(optional = false)
	private Course course;

	@ManyToMany
	private Collection<User> participants;

	@NotNull
	private LocalDateTime startDate;

	@NotNull
	private LocalDateTime endDate;

	@OrderColumn
	@Column(unique = true)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Lecture> lectures = new ArrayList<>();
}
