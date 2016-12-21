package org.nhnnext.nextstep.lecture;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.nextstep.core.ObjectConverter;
import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.course.domain.AbstractCourseEntity;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.session.Session;
import org.nhnnext.nextstep.session.domain.AbstractCourseSessionEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class Lecture extends AbstractCourseSessionEntity {

    @NotEmpty
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Session session;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecture")
    private final List<Lesson> lessons = new ArrayList<>();

    @Convert(converter = ObjectConverter.class)
    private Object pos = new ArrayList<>();

    public void addToLessons(Lesson lesson) {
        getLessons().add(lesson);
        lesson.setLecture(this);
    }

    public boolean isInstructor(Authentication authentication) {
        Assert.notNull(getSession());
        return getSession().isInstructor(authentication);
    }

    public boolean isParticipant(Authentication authentication) {
        Assert.notNull(getSession());

        if (!(getSession() instanceof CourseSession)) {
            return false;
        }

        return ((CourseSession) getSession()).isParticipant(authentication);
    }
}
