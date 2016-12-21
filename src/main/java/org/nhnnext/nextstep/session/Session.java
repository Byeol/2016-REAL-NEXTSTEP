package org.nhnnext.nextstep.session;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.nhnnext.nextstep.core.ObjectConverter;
import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.course.domain.AbstractCourseEntity;
import org.nhnnext.nextstep.lecture.Lecture;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(force = true)
@Data
@Entity
@Inheritance
public abstract class Session extends AbstractCourseEntity {

    @Convert(converter = ObjectConverter.class)
    private Object pos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
    private final List<Lecture> lectures = new ArrayList<>();

    public void addToLectures(Lecture lecture) {
        getLectures().add(lecture);
        lecture.setSession(this);
    }

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Course course;

    public boolean isInstructor(Authentication authentication) {
        Assert.notNull(getCourse());
        return getCourse().isInstructor(authentication);
    }
}
