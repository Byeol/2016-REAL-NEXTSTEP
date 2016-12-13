package org.nhnnext.nextstep.course.web;

import lombok.RequiredArgsConstructor;
import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.user.Instructor;
import org.nhnnext.nextstep.user.UserService;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
@RepositoryEventHandler
public class CourseEventHandler {

    private final UserService userService;

//    @HandleBeforeCreate
//    public void handleCourseCreate(Course course) {
//        userService.getAuthenticatedUser()
//                .map(user -> (Instructor) user)
//                .ifPresent(course.getInstructors()::add);
//    }

//    @HandleBeforeCreate
//    public void handleCourseCreate(Course course) {
//        System.out.println("HERE");
//        course.getSessions().add(new CourseSession("default"));
//        course.setMasterSession(new MasterSession());
//    }

}
