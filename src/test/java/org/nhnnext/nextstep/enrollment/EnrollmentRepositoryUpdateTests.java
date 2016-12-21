package org.nhnnext.nextstep.enrollment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

public class EnrollmentRepositoryUpdateTests extends AbstractIntegratedRepositoryTest<Enrollment, EnrollmentRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        Assert.assertEquals(entity.getStatus(), Enrollment.Status.PENDING);
        entity.setStatus(Enrollment.Status.APPROVED);
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(entity));
    }

    @Test
    public void withMockUser() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        Assert.assertEquals(entity.getStatus(), Enrollment.Status.PENDING);
        entity.setStatus(Enrollment.Status.APPROVED);
        thrown.expect(AccessDeniedException.class);
        withMockUser(() -> save(entity));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        Assert.assertEquals(entity.getStatus(), Enrollment.Status.PENDING);
        entity.setStatus(Enrollment.Status.APPROVED);
        withMockInstructor(() -> save(entity));
        Enrollment result = (Enrollment) withMockInstructor(() -> findOne(entity.getId()));
        Assert.assertEquals(result.getStatus(), Enrollment.Status.APPROVED);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        Assert.assertEquals(entity.getStatus(), Enrollment.Status.PENDING);
        entity.setStatus(Enrollment.Status.APPROVED);
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> save(entity));
    }
}
