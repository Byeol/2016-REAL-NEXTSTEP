package org.nhnnext.nextstep.course;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CourseRepositoryReadTests extends AbstractCourseRepositoryTest {

    @Before
    public void init() {
        super.init();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Course entity = (Course) withMockInstructor(() -> save(createCourse()));
        Course result = (Course) withAnonymousUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockUser() throws Exception {
        Course entity = (Course) withMockInstructor(() -> save(createCourse()));
        Course result = (Course) withMockUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockInstructor() throws Exception {
        Course entity = (Course) withMockInstructor(() -> save(createCourse()));
        Course result = (Course) withMockInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Course entity = (Course) withMockInstructor(() -> save(createCourse()));
        Course result = (Course) withMockAlternativeInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }
}
