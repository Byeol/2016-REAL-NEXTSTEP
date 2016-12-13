package org.nhnnext.nextstep.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.nhnnext.nextstep.course.Course;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(force = true)
@Data
@Entity
@DiscriminatorValue("INSTRUCTOR")
public class Instructor extends User {

    public Instructor(String username) {
        super(username);
    }

//    @Column(unique = true)
//    @ManyToMany(mappedBy = "instructors")
//    @OneToMany(mappedBy = "createdBy")
//    private final List<Course> courses = new ArrayList<>();

    //    private final List<Session> sessions = new ArrayList<>();
}
