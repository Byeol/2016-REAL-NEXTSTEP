package org.nhnnext.nextstep.discussion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

public class DiscussionRepositoryCreateTests extends AbstractIntegratedRepositoryTest<Discussion, DiscussionRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Discussion discussion = createDiscussion();
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(discussion));
    }

    @Test
    public void withMockUser() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockUser(() -> save(discussion));
        Discussion result = (Discussion) withMockUser(() -> findOne(entity.getId()));
        Assert.assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeUser() throws Exception {
        Discussion discussion = createDiscussion();
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeUser(() -> save(discussion));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Discussion discussion = createDiscussion();
        Discussion entity = (Discussion) withMockInstructor(() -> save(discussion));
        Discussion result = (Discussion) withMockInstructor(() -> findOne(entity.getId()));
        Assert.assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Discussion discussion = createDiscussion();
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> save(discussion));
    }
}
