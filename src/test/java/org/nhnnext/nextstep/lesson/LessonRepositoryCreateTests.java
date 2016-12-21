package org.nhnnext.nextstep.lesson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

public class LessonRepositoryCreateTests extends AbstractIntegratedRepositoryTest<Lesson, LessonRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Lesson lesson = createLesson();
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(lesson));
    }

    @Test
    public void withMockUser() throws Exception {
        Lesson lesson = createLesson();
        thrown.expect(AccessDeniedException.class);
        withMockUser(() -> save(lesson));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));
        Lesson result = (Lesson) withMockInstructor(() -> findOne(entity.getId()));
        Assert.assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Lesson lesson = createLesson();
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> save(lesson));
    }
}
