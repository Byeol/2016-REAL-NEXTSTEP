package org.nhnnext.domain;

import lombok.Data;
import org.nhnnext.domain.auditing.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Lecture extends AbstractPersistable<Long> {

	@NotNull
	private String title;

	@ManyToOne(optional = false)
	private Course course;

	@OneToMany(mappedBy = "lecture")
//	@OrderColumn(name = "course_order")
	private List<Lesson> lessons;
}
