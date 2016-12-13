package org.nhnnext.nextstep.course;

import org.junit.Test;
import org.nhnnext.nextstep.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

public class CourseRepositoryDeleteTests extends AbstractIntegrationTest {

    @Autowired
    CourseRepository repository;

    private void delete(Course entity) {
        repository.delete(entity);
    }

    @Test(expected = AccessDeniedException.class)
    @WithAnonymousUser
    public void withAnonymousUser() {
        Course entity = saveWithMockInstructor(createCourse());
        delete(entity);
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser
    public void withMockUser() {
        Course entity = saveWithMockInstructor(createCourse());
        delete(entity);
    }

    @Test
    @WithMockUser(roles = "INSTRUCTOR")
    public void withMockInstructor() {
        Course entity = saveWithMockInstructor(createCourse());
        delete(entity);
    }

    @WithMockUser(roles = "INSTRUCTOR")
    private Course saveWithMockInstructor(Course entity) {
        return repository.save(entity);
    }

    public static Course createCourse() {
        Course course = new Course();
        course.setName("name");
        course.setDescription("description");
        return course;
    }
}
