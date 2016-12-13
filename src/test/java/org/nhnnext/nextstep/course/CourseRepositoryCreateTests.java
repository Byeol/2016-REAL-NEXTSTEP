package org.nhnnext.nextstep.course;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.AbstractIntegrationTest;
import org.nhnnext.nextstep.user.Instructor;
import org.nhnnext.nextstep.user.User;
import org.nhnnext.nextstep.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class CourseRepositoryCreateTests extends AbstractIntegrationTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository repository;

    @Before
    public void init() {
        repository.deleteAll();
        userRepository.deleteAll();

        User user = new User("testuser");
        user.setName("Test user");
        userRepository.save(user);

        Instructor instructor = new Instructor("instructor");
        instructor.setName("Test instructor");
        userRepository.save(instructor);
    }

    @Test(expected = AccessDeniedException.class)
    @WithAnonymousUser
    public void withAnonymousUser() {
        repository.save(createCourse());
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser(username = "testuser")
    public void withMockUser() {
        repository.save(createCourse());
    }

    @Test
    @WithMockUser(username = "instructor", roles = "INSTRUCTOR")
    public void withMockInstructor() {
        Course course = test(); //repository.save(createCourse());
        System.out.println(course.getId());
        System.out.println(course.getCreatedBy());
        course = repository.findOne(course.getId());
        assertThat(course.getInstructors(), hasSize(1));
    }

    @Transactional
    public Course test() {
        return repository.save(createCourse());
    }

    public static Course createCourse() {
        Course course = new Course();
        course.setName("name");
        course.setDescription("description");
        return course;
    }
}
