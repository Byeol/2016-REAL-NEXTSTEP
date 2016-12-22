package org.nhnnext.nextstep.discussion;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

public class DiscussionRepositoryDeleteTests extends AbstractIntegratedRepositoryTest<Discussion, DiscussionRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> delete(entity));
    }

    @Test
    public void withMockUser() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        withMockUser(() -> delete(entity));
    }

    @Test
    public void withMockAlternativeUser() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeUser(() -> delete(entity));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        thrown.expect(AccessDeniedException.class);
        withMockInstructor(() -> delete(entity));
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> delete(entity));
    }
}
