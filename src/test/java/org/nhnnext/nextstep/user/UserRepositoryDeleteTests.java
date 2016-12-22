package org.nhnnext.nextstep.user;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

public class UserRepositoryDeleteTests extends AbstractIntegratedRepositoryTest<User, UserRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> delete(entity));
    }

    @Test
    public void withMockUser() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        withMockUser(() -> delete(entity));
    }

    @Test
    public void withMockAlternativeUser() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeUser(() -> delete(entity));
    }

    @Test
    public void withMockInstructor() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        thrown.expect(AccessDeniedException.class);
        withMockInstructor(() -> delete(entity));
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> delete(entity));
    }
}
