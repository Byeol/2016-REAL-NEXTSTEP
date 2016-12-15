package org.nhnnext.nextstep.course;

import org.nhnnext.nextstep.core.AbstractRepositoryTest;

public abstract class AbstractCourseRepositoryTest extends AbstractRepositoryTest<Course, CourseRepository> {

    public void init() {
        initRepository();
        initUser();
    }

    public static Course createCourse() {
        Course course = new Course();
        course.setName("name");
        course.setDescription("description");
        return course;
    }
}
