package org.nhnnext.nextstep.session;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@NoArgsConstructor(force = true)
@Data
@Entity
@DiscriminatorValue(SessionType.Values.COURSE)
public class CourseSession extends Session {

    public CourseSession(String name) {
        super(name);
    }

    private LocalDateTime startDate;
    private LocalDateTime endDate;

//    @OneToMany(mappedBy = "session")
//    private final List<Enrollment> enrollments = new ArrayList<>();
//
//    public List<User> getParticipants() {
//        return this.enrollments.stream()
//                .filter(Enrollment::isApproved)
//                .map(Enrollment::getUser)
//                .collect(Collectors.toList());
//    }
}
