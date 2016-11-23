package org.nhnnext.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Lesson extends AbstractPersistable<Long> {

	@NotEmpty
	private String title;

	@NotEmpty
	private String content;

	@ManyToOne(optional = false)
	private Lecture lecture; // should be final

	private Access access;

	public enum Access {
		PUBLIC,
		PRIVATE
	}
}
