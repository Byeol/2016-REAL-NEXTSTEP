package org.nhnnext.nextstep.course;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.nextstep.core.AbstractEntity;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.user.Instructor;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

//@NoArgsConstructor(force = true)
@Data
@Entity
public class Course extends AbstractEntity {

    @NotEmpty
    private String name;

    private String description;

    @Column(unique = true)
    @ManyToMany
    private final List<Instructor> instructors = new ArrayList<>();

//    @Column(unique = true)
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", orphanRemoval = true)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")//(mappedBy = "course", fetch = FetchType.LAZY)
//    @Cascade(CascadeType.ALL)
    private final List<CourseSession> sessions = new ArrayList<>();

    public void addToSessions(CourseSession session) {
        this.getSessions().add(session);
        session.setCourse(this);
    }

    //    @OneToOne(cascade = CascadeType.ALL, mappedBy = "course")
//    private MasterSession masterSession;
}
