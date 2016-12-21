package org.nhnnext.nextstep.session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CourseSessionRepositoryDeleteTests extends AbstractIntegratedRepositoryTest<CourseSession, MySessionRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> delete(entity));
    }

    @Test
    public void withMockUser() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        thrown.expect(AccessDeniedException.class);
        withMockUser(() -> delete(entity));
    }

    @Test
    public void withMockInstructor() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        withMockInstructor(() -> delete(entity));
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> delete(entity));
    }
}
