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

public class UserResourceIntegrationTests extends AbstractIntegratedRepositoryTest<User, UserRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void getExcerpt() throws Exception {
        User entity = (User) withMockUser(this::getMockUser);

        mvc.perform(get("/api/users/" + entity.getId() + "?projection=excerpt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.name", equalTo(entity.getName())))
                .andExpect(jsonPath("$.username", equalTo(entity.getUsername())))
                .andExpect(jsonPath("$.avatarUrl", equalTo(entity.getAvatarUrl())))
                .andExpect(jsonPath("$.role", equalTo(entity.getRole())));
    }
}
