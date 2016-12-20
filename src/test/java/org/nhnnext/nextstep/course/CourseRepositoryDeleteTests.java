package org.nhnnext.nextstep.course;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

public class CourseRepositoryDeleteTests extends AbstractIntegratedRepositoryTest<Course, CourseRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Course entity = (Course) withMockInstructor(() -> save(createCourse()));
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> delete(entity));
    }

    @Test
    public void withMockUser() throws Exception {
        Course entity = (Course) withMockInstructor(() -> save(createCourse()));
        thrown.expect(AccessDeniedException.class);
        withMockUser(() -> delete(entity));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Course entity = (Course) withMockInstructor(() -> save(createCourse()));
        withMockInstructor(() -> delete(entity));
        thrown.expect(AccessDeniedException.class);
        withMockInstructor(() -> findOne(entity.getId()));
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Course entity = (Course) withMockInstructor(() -> save(createCourse()));
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> delete(entity));
    }
}
