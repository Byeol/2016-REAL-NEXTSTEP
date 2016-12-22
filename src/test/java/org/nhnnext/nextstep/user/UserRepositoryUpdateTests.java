package org.nhnnext.nextstep.user;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.course.CourseRepository;
import org.nhnnext.nextstep.discussion.Discussion;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserRepositoryUpdateTests extends AbstractIntegratedRepositoryTest<User, UserRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        entity.setName("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(entity));
    }

    @Test
    public void withMockUser() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        entity.setName("UPDATE");
        withMockUser(() -> {
            assertNotEquals(findOne(entity.getId()).getName(), "UPDATE");
            save(entity);
            assertEquals(findOne(entity.getId()).getName(), "UPDATE");
        });
    }

    @Test
    public void withMockAlternativeUser() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        entity.setName("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeUser(() -> save(entity));
    }

    @Test
    public void withMockInstructor() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        entity.setName("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withMockInstructor(() -> save(entity));
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        entity.setName("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> save(entity));
    }
}
