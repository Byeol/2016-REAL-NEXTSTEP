package org.nhnnext.nextstep.discussion;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.lesson.LessonRepository;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DiscussionRepositoryUpdateTests extends AbstractIntegratedRepositoryTest<Discussion, DiscussionRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        entity.setComment("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(entity));
    }

    @Test
    public void withMockUser() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        entity.setComment("UPDATE");
        withMockUser(() -> {
            assertNotEquals(findOne(entity.getId()).getComment(), "UPDATE");
            save(entity);
            assertEquals(findOne(entity.getId()).getComment(), "UPDATE");
        });
    }

    @Test
    public void withMockAlternativeUser() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        entity.setComment("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeUser(() -> save(entity));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        entity.setComment("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withMockInstructor(() -> save(entity));
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        entity.setComment("UPDATE");
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> save(entity));
    }
}
