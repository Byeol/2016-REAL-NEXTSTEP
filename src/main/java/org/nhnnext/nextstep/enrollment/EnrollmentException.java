package org.nhnnext.nextstep.enrollment;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class EnrollmentException extends RuntimeException {

    private final Enrollment enrollment;

    public EnrollmentException(Enrollment enrollment, String message) {
        super(message);

        Assert.notNull(enrollment);
        this.enrollment = enrollment;
    }
}