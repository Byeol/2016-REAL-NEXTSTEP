package org.nhnnext.nextstep.web;

import lombok.RequiredArgsConstructor;
import org.nhnnext.nextstep.course.CourseRepository;
import org.nhnnext.nextstep.user.Instructor;
import org.nhnnext.nextstep.user.User;
import org.nhnnext.nextstep.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MyNewTestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository repository;

    @GetMapping("test")
    public Object test() {
        repository.deleteAll();
        userRepository.deleteAll();

        User user = new User("testuser");
        user.setName("Test user");
        userRepository.save(user);

        Instructor instructor = new Instructor("instructor");
        instructor.setName("Test instructor");
        userRepository.save(instructor);

        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }
}
