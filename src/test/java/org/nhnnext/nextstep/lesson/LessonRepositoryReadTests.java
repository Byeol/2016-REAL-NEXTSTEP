package org.nhnnext.nextstep.lesson;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.Assert.assertEquals;

public class LessonRepositoryReadTests extends AbstractIntegratedRepositoryTest<Lesson, LessonRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void publicWithAnonymousUser() throws Exception {
        Lesson lesson = createLesson();
        setLessonAsPublic(lesson);
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        Lesson result = (Lesson) withAnonymousUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> findOne(entity.getId()));
    }

    @Test
    public void withMockUser() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        Lesson result = (Lesson) withMockUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeUser() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeUser(() -> findOne(entity.getId()));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        Lesson result = (Lesson) withMockInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> findOne(entity.getId()));
    }
}
