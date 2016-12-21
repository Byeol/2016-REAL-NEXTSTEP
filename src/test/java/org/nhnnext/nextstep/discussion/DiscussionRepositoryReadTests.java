package org.nhnnext.nextstep.discussion;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.lesson.LessonRepository;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.Assert.assertEquals;

public class DiscussionRepositoryReadTests extends AbstractIntegratedRepositoryTest<Discussion, DiscussionRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> findOne(entity.getId()));
    }

    @Test
    public void withMockUser() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        Discussion result = (Discussion) withMockUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withAlternativeMockUser() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeUser(() -> findOne(entity.getId()));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        Discussion result = (Discussion) withMockInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> findOne(entity.getId()));
    }
}
