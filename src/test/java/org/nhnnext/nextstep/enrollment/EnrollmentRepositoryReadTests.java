package org.nhnnext.nextstep.enrollment;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;

import static org.junit.Assert.assertEquals;

public class EnrollmentRepositoryReadTests extends AbstractIntegratedRepositoryTest<Enrollment, EnrollmentRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        Enrollment result = (Enrollment) withAnonymousUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockUser() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        Enrollment result = (Enrollment) withMockUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockInstructor() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        Enrollment result = (Enrollment) withMockInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));
        Enrollment result = (Enrollment) withMockAlternativeInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }
}
