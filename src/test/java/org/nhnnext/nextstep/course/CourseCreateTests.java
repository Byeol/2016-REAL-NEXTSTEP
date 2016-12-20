package org.nhnnext.nextstep.course;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CourseCreateTests extends AbstractIntegratedRepositoryTest<Course, CourseRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withMockInstructor() throws Exception {
        Course course = createCourse();
        Course entity = (Course) withMockInstructor(() -> save(course));
        Course result = (Course) withMockInstructor(() -> findOne(entity.getId()));

        assertTrue(result.getSession("master").isPresent());
        assertTrue(result.getSession("default").isPresent());
        assertFalse(result.getSession("nonexistent").isPresent());
    }
}