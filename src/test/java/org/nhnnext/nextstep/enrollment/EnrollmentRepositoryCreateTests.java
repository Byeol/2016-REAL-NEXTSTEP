package org.nhnnext.nextstep.enrollment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

public class EnrollmentRepositoryCreateTests extends AbstractIntegratedRepositoryTest<Enrollment, EnrollmentRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Enrollment enrollment = createEnrollment();
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(enrollment));
    }

    @Test
    public void withMockUser() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        Enrollment result = (Enrollment) withMockInstructor(() -> findOne(entity.getId()));
        Assert.assertEquals(entity, result);
    }

    @Test
    public void withMockInstructor() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockInstructor(() -> save(enrollment));
        Enrollment result = (Enrollment) withMockInstructor(() -> findOne(entity.getId()));
        Assert.assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockAlternativeInstructor(() -> save(enrollment));
        Enrollment result = (Enrollment) withMockInstructor(() -> findOne(entity.getId()));
        Assert.assertEquals(entity, result);
    }
}
