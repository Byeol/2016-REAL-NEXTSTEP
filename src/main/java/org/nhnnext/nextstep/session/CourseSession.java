package org.nhnnext.nextstep.session;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.nextstep.enrollment.Enrollment;
import org.nhnnext.nextstep.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(force = true)
@Data
@Entity
@DiscriminatorValue("COURSE")
public class CourseSession extends Session {

    public CourseSession(String name) {
        this.name = name;
    }

    @NotEmpty
    private String name;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "session")
    private final List<Enrollment> enrollments = new ArrayList<>();

    public List<User> getParticipants() {
        return this.enrollments.stream()
                .filter(Enrollment::isApproved)
                .map(Enrollment::getUser)
                .collect(Collectors.toList());
    }
}
