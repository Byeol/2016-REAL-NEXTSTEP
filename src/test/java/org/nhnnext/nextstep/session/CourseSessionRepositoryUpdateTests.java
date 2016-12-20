package org.nhnnext.nextstep.session;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CourseSessionRepositoryUpdateTests extends AbstractIntegratedRepositoryTest<Session, MySessionRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        entity.setDescription("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(entity));
    }

    @Test
    public void withMockUser() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        entity.setDescription("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withMockUser(() -> save(entity));
    }

    @Test
    public void withMockInstructor() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        entity.setDescription("UPDATE");
        withMockInstructor(() -> {
            assertNotEquals(findOne(entity.getId()).getDescription(), "UPDATE");
            save(entity);
            assertEquals(findOne(entity.getId()).getDescription(), "UPDATE");
        });
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        entity.setDescription("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> save(entity));
    }
}
