package org.nhnnext.nextstep.core;

import org.nhnnext.nextstep.user.Instructor;
import org.nhnnext.nextstep.user.User;
import org.nhnnext.nextstep.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;

public abstract class AbstractIntegrationTestWithUser extends AbstractWebIntegrationTest {

    private static final String USER_USERNAME = "user";
    private static final String INSTRUCTOR_USERNAME = "instructor";
    private static final String ALTERNATIVE_INSTRUCTOR_USERNAME = "instructor2";

    @Autowired
    private UserRepository userRepository;

    protected void initUser() {
        userRepository.deleteAll();

        User user = new User(USER_USERNAME);
        user.setName("Test user");
        userRepository.save(user);

        user = new Instructor(INSTRUCTOR_USERNAME);
        user.setName("Test instructor");
        userRepository.save(user);

        user = new Instructor(ALTERNATIVE_INSTRUCTOR_USERNAME);
        user.setName("Test instructor");
        userRepository.save(user);
    }

    private User loadUser(String username) {
        return userRepository.findByUsername(username).get();
    }

    protected Object withAnonymousUser(Callable callable) throws Exception {
        return SecurityUtils.runAs(SecurityUtils.withAnonymousUser(), callable);
    }

    protected void withAnonymousUser(Runnable runnable) {
        SecurityUtils.runAs(SecurityUtils.withAnonymousUser(), runnable);
    }

    protected Object withMockUser(Callable callable) throws Exception {
        return SecurityUtils.runAs(SecurityUtils.withMockUser(loadUser(USER_USERNAME)), callable);
    }

    protected void withMockUser(Runnable runnable) {
        SecurityUtils.runAs(SecurityUtils.withMockUser(loadUser(USER_USERNAME)), runnable);
    }

    protected Object withMockInstructor(Callable callable) throws Exception {
        return SecurityUtils.runAs(SecurityUtils.withMockUser(loadUser(INSTRUCTOR_USERNAME)), callable);
    }

    protected void withMockInstructor(Runnable runnable) {
        SecurityUtils.runAs(SecurityUtils.withMockUser(loadUser(INSTRUCTOR_USERNAME)), runnable);
    }

    protected Object withMockAlternativeInstructor(Callable callable) throws Exception {
        return SecurityUtils.runAs(SecurityUtils.withMockUser(loadUser(ALTERNATIVE_INSTRUCTOR_USERNAME)), callable);
    }

    protected void withMockAlternativeInstructor(Runnable runnable) {
        SecurityUtils.runAs(SecurityUtils.withMockUser(loadUser(ALTERNATIVE_INSTRUCTOR_USERNAME)), runnable);
    }
}
