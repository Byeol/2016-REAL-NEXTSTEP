package org.nhnnext.nextstep.core;

import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.course.CourseRepository;
import org.nhnnext.nextstep.discussion.Discussion;
import org.nhnnext.nextstep.discussion.DiscussionReply;
import org.nhnnext.nextstep.discussion.DiscussionRepository;
import org.nhnnext.nextstep.enrollment.Enrollment;
import org.nhnnext.nextstep.enrollment.EnrollmentRepository;
import org.nhnnext.nextstep.lecture.Lecture;
import org.nhnnext.nextstep.lecture.LectureRepository;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.lesson.LessonRepository;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.session.MySessionRepository;
import org.nhnnext.nextstep.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public abstract class AbstractIntegratedRepositoryTest<T, R extends CrudRepository> extends AbstractRepositoryTest<T, R> {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MySessionRepository sessionRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private DiscussionRepository discussionRepository;

    public void initRepository() {
        withMockAdmin(() -> {
            lessonRepository.deleteAll();
            lectureRepository.deleteAll();
            sessionRepository.deleteAll();
            courseRepository.deleteAll();
        });
        initUser();
    }

    public User createUser() {
        User user = new User("test");
        user.setName("name");
        user.setEmail("exmaple@domain.com");
        user.setAvatarUrl("http://example.com");
        return user;
    }

    public Course createCourse() {
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

    public Enrollment createEnrollment() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession sessionEntity = (CourseSession) withMockInstructor(() -> sessionRepository.save(session));
        Enrollment entity = new Enrollment();
        sessionEntity.addToEnrollments(entity);
        return entity;
    }

    public void addEnrollmentToSession(CourseSession session) throws Exception {
        Enrollment enrollment = new Enrollment();
        session.addToEnrollments(enrollment);
        withMockUser(() -> enrollmentRepository.save(enrollment));
        enrollment.setStatus(Enrollment.Status.APPROVED);
        withMockInstructor(() -> enrollmentRepository.save(enrollment));
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
        addEnrollmentToSession((CourseSession) lectureEntity.getSession());
        Lesson entity = new Lesson();
        entity.setName("name");
        entity.setContent("content");
        lectureEntity.addToLessons(entity);
        return entity;
    }

    public void setLessonAsPublic(Lesson lesson) throws Exception {
        lesson.setAccess(Lesson.Access.PUBLIC);
        withMockInstructor(() -> lessonRepository.save(lesson));
    }

    public Discussion createDiscussion() throws Exception {
        Lesson lesson = createLesson();
        Lesson lessonEntity = (Lesson) withMockInstructor(() -> lessonRepository.save(lesson));
        Discussion entity = new Discussion();
        entity.setComment("comment");
        lessonEntity.addToDiscussions(entity);
        return entity;
    }

    public DiscussionReply createDiscussionReply() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion discussionReply = (Discussion) withMockInstructor(() -> discussionRepository.save(discussion));
        DiscussionReply entity = new DiscussionReply();
        entity.setComment("comment");
        discussionReply.addToReplies(entity);
        return entity;
    }
}
