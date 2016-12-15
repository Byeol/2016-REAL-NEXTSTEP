package org.nhnnext.nextstep.session;

import org.nhnnext.nextstep.core.AbstractRepositoryTest;
import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCourseSessionRepositoryTest extends AbstractRepositoryTest<CourseSession, CourseSessionRepository> {

    @Autowired
    private CourseRepository courseRepository;

    public void init() {
        initRepository();
        courseRepository.deleteAll();
        initUser();
    }

    public static Course createCourse() {
        Course course = new Course();
        course.setName("name");
        course.setDescription("description");
        return course;
    }

    public CourseSession createCourseSession() throws Exception {
        Course course = (Course) withMockInstructor(() -> courseRepository.save(createCourse()));
        CourseSession session = new CourseSession();
        session.setName("name");
        course.addToSessions(session);
        return session;
    }
}
