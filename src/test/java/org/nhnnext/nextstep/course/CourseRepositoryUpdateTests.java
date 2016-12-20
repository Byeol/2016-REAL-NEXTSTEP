package org.nhnnext.nextstep.course;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CourseRepositoryUpdateTests extends AbstractIntegratedRepositoryTest<Course, CourseRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Course entity = (Course) withMockInstructor(() -> save(createCourse()));
        entity.setDescription("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(entity));
    }

    @Test
    public void withMockUser() throws Exception {
        Course entity = (Course) withMockInstructor(() -> save(createCourse()));
        entity.setDescription("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withMockUser(() -> save(entity));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Course entity = (Course) withMockInstructor(() -> save(createCourse()));
        entity.setDescription("UPDATE");
        withMockInstructor(() -> {
            assertNotEquals(findOne(entity.getId()).getDescription(), "UPDATE");
            save(entity);
            assertEquals(findOne(entity.getId()).getDescription(), "UPDATE");
        });
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Course entity = (Course) withMockInstructor(() -> save(createCourse()));
        entity.setDescription("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> save(entity));
    }
}
