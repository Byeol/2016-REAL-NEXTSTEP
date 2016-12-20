package org.nhnnext.nextstep.lesson;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.nextstep.core.domain.AbstractEntity;
import org.nhnnext.nextstep.lecture.Lecture;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Lesson extends AbstractEntity {

    @NotNull
    private Access access = Access.PRIVATE;

    public enum Access {
        PUBLIC,
        PRIVATE
    }

    @NotEmpty
    private String name;

    @NotEmpty
    private String content;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Lecture lecture;

}
