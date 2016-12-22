package org.nhnnext.nextstep.user;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;

import static org.junit.Assert.assertEquals;

public class UserRepositoryCreateTests extends AbstractIntegratedRepositoryTest<User, UserRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        User user = createUser();
        User entity = (User) withAnonymousUser(() -> save(user));
        User result = (User) withAnonymousUser(() -> findOne(user.getId()));
        assertEquals(entity, result);
    }

}
