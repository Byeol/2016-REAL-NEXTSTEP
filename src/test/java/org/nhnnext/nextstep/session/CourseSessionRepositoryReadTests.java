package org.nhnnext.nextstep.session;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.course.Course;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CourseSessionRepositoryReadTests extends AbstractIntegratedRepositoryTest<Session, MySessionRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        CourseSession result = (CourseSession) withAnonymousUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockUser() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        CourseSession result = (CourseSession) withMockUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockInstructor() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        CourseSession result = (CourseSession) withMockInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        CourseSession result = (CourseSession) withMockAlternativeInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }
}
