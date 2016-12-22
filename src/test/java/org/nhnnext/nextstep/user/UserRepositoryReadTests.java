package org.nhnnext.nextstep.user;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;

import static org.junit.Assert.assertEquals;

public class UserRepositoryReadTests extends AbstractIntegratedRepositoryTest<User, UserRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        User result = (User) withAnonymousUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockUser() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        User result = (User) withMockUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeUser() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        User result = (User) withMockAlternativeUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockInstructor() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        User result = (User) withMockInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);
        User result = (User) withMockAlternativeInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }
}
