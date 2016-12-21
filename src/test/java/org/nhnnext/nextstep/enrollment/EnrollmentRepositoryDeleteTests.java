package org.nhnnext.nextstep.enrollment;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

public class EnrollmentRepositoryDeleteTests extends AbstractIntegratedRepositoryTest<Enrollment, EnrollmentRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> delete(entity));
    }

    @Test
    public void withMockUser() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        withMockUser(() -> delete(entity));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        thrown.expect(AccessDeniedException.class);
        withMockInstructor(() -> delete(entity));
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> delete(entity));
    }
}
