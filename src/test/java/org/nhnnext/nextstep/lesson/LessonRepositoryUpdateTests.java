package org.nhnnext.nextstep.lesson;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LessonRepositoryUpdateTests extends AbstractIntegratedRepositoryTest<Lesson, LessonRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        entity.setName("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(entity));
    }

    @Test
    public void withMockUser() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        entity.setName("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withMockUser(() -> save(entity));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        entity.setName("UPDATE");
        withMockInstructor(() -> {
            assertNotEquals(findOne(entity.getId()).getName(), "UPDATE");
            save(entity);
            assertEquals(findOne(entity.getId()).getName(), "UPDATE");
        });
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        entity.setName("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> save(entity));
    }
}
