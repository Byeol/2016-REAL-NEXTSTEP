package org.nhnnext.nextstep.lesson;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;

import static org.junit.Assert.assertEquals;

public class LessonRepositoryReadTests extends AbstractIntegratedRepositoryTest<Lesson, LessonRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        Lesson result = (Lesson) withAnonymousUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockUser() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        Lesson result = (Lesson) withMockUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
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
        Lesson result = (Lesson) withMockAlternativeInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }
}
