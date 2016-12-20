package org.nhnnext.nextstep.core;

import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.course.CourseRepository;
import org.nhnnext.nextstep.lecture.Lecture;
import org.nhnnext.nextstep.lecture.LectureRepository;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.lesson.LessonRepository;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.session.MySessionRepository;
import org.nhnnext.nextstep.user.User;
import org.nhnnext.nextstep.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertNotNull;

public abstract class AbstractIntegratedRepositoryTest<T, R extends CrudRepository> extends AbstractRepositoryTest<T, R> {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MySessionRepository sessionRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public void initRepository() {
        lessonRepository.deleteAll();
        lectureRepository.deleteAll();
        sessionRepository.deleteAll();
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
        Course course = createCourse();
        Course courseEntity = (Course) withMockInstructor(() -> courseRepository.save(course));
        CourseSession session = new CourseSession("test");
        session.setDescription("description");
        courseEntity.addToSessions(session);
        return session;
    }

    public Lecture createLecture() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession sessionEntity = (CourseSession) withMockInstructor(() -> sessionRepository.save(session));
        Lecture entity = new Lecture();
        entity.setName("name");
        sessionEntity.addToLectures(entity);
        return entity;
    }

    public Lesson createLesson() throws Exception {
        Lecture lecture = createLecture();
        Lecture lectureEntity = (Lecture) withMockInstructor(() -> lectureRepository.save(lecture));
        Lesson entity = new Lesson();
        entity.setName("name");
        entity.setContent("content");
        lectureEntity.addToLessons(entity);
        return entity;
    }
}
