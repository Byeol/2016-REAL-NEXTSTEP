package org.nhnnext.nextstep.user.web;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.course.CourseRepository;
import org.nhnnext.nextstep.user.User;
import org.nhnnext.nextstep.user.UserRepository;
import org.springframework.hateoas.MediaTypes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTests extends AbstractIntegratedRepositoryTest<User, UserRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void getAuthenticatedUser() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);

        withMockUser(() -> mvc.perform(get("/api/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.user.username", equalTo(entity.getUsername())))
                .andExpect(jsonPath("$.user.name", equalTo(entity.getName())))
                .andExpect(jsonPath("$.user.email", equalTo(entity.getEmail())))
                .andExpect(jsonPath("$.user.avatarUrl", equalTo(entity.getAvatarUrl())))
                .andExpect(jsonPath("$.authorities[0].authority", equalTo(entity.getRole())))
        );
    }
}
