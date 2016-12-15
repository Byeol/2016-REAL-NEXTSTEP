package org.nhnnext.nextstep.enrollment;

import lombok.Data;
import lombok.ToString;
import org.nhnnext.nextstep.core.domain.AbstractEntity;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.user.User;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@ToString(callSuper = true)
public abstract class Enrollment extends AbstractEntity {

    @ManyToOne(optional = false)
    private final CourseSession session;

    @ManyToOne(optional = false)
    private final User user;

    public Enrollment(CourseSession session, User user) {
        this.session = session;
        this.user = user;
        this.status = Status.PENDING;
    }

    public Enrollment() {
        this(null, null);
    }

    private Status status;

    public static enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    public boolean isApproved() {
        return Enrollment.Status.APPROVED.equals(this.status);
    }

}
