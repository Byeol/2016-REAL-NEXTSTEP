package org.nhnnext.nextstep.session;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.nhnnext.nextstep.enrollment.Enrollment;
import org.nhnnext.nextstep.enrollment.EnrollmentException;
import org.nhnnext.nextstep.user.AuthenticationUtils;
import org.nhnnext.nextstep.user.User;
import org.springframework.security.core.Authentication;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
@DiscriminatorValue(SessionType.Values.COURSE)
public class CourseSession extends Session {

    @NotNull
    private final String name;

    private String description;

    @NotNull
    private State state = State.IN_SESSION;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
    private final List<Enrollment> enrollments = new ArrayList<>();

    @JsonIgnore
    @Transient
    public boolean isEnrolled(Authentication authentication) {
        return isEnrolled(AuthenticationUtils.getUser(authentication));
    }

    @JsonIgnore
    @Transient
    public boolean isEnrolled(User user) {
        return getEnrollments().stream()
                .map(Enrollment::getUser)
                .collect(Collectors.toList())
                .contains(user);
    }

    @JsonIgnore
    @Transient
    public List<User> getParticipants() {
        return this.enrollments.stream()
                .filter(Enrollment::isApproved)
                .map(Enrollment::getUser)
                .collect(Collectors.toList());
    }

    @JsonIgnore
    @Transient
    public boolean isParticipant(User user) {
        return getParticipants().contains(user);
    }

    @JsonIgnore
    @Transient
    public boolean isParticipant(Authentication authentication) {
        return isParticipant(AuthenticationUtils.getUser(authentication));
    }

    public void addToEnrollments(Enrollment enrollment) {
        if (isEnrolled(enrollment.getUser())) {
            throw new EnrollmentException(enrollment, "User already enrolled!");
        }

        getEnrollments().add(enrollment);
        enrollment.setSession(this);

    }
    public enum State {
        UPCOMING, IN_SESSION
    }
}
