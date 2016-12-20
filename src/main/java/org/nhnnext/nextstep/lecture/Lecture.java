package org.nhnnext.nextstep.lecture;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.nextstep.core.ObjectConverter;
import org.nhnnext.nextstep.core.domain.AbstractEntity;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.session.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(force = true)
@Data
@Entity
public class Lecture extends AbstractEntity {

    @Convert(converter = ObjectConverter.class)
    private Object sessionPos = new ArrayList<Object>();

    @NotEmpty
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Session session;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "lecture")//(mappedBy = "course", fetch = FetchType.LAZY)
//    @Cascade(CascadeType.ALL)
    private final List<Lesson> lessons = new ArrayList<>();

    public void addToLessons(Lesson lesson) {
        getLessons().add(lesson);
        lesson.setLecture(this);
    }

}
