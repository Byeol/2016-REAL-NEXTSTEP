package org.nhnnext.nextstep.course;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.Assert.assertEquals;

public class CourseRepositoryCreateTests extends AbstractCourseRepositoryTest {

    @Before
    public void init() {
        super.init();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Course course = createCourse();
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(course));
    }

    @Test
    public void withMockUser() throws Exception {
        Course course = createCourse();
        thrown.expect(AccessDeniedException.class);
        withMockUser(() -> save(course));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Course course = createCourse();
        Course expected = (Course) withMockInstructor(() -> save(course));
        Course result = (Course) withMockInstructor(() -> findOne(expected.getId()));
        assertEquals(expected, result);
    }
}
