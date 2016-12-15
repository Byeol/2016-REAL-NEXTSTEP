package org.nhnnext.nextstep.session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.course.Course;
import org.springframework.security.access.AccessDeniedException;

public class CourseSessionRepositoryCreateTests extends AbstractCourseSessionRepositoryTest {

    @Before
    public void init() {
        super.init();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        CourseSession session = createCourseSession();
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(session));
    }

    @Test
    public void withMockUser() throws Exception {
        CourseSession session = createCourseSession();
        thrown.expect(AccessDeniedException.class);
        withMockUser(() -> save(session));
    }

    @Test
    public void withMockInstructor() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));
        CourseSession result = (CourseSession) withMockInstructor(() -> findOne(entity.getId()));
        Assert.assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        CourseSession session = createCourseSession();
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> save(session));
    }
}
