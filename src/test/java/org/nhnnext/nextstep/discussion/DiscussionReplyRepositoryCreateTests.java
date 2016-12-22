package org.nhnnext.nextstep.discussion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

public class DiscussionReplyRepositoryCreateTests extends AbstractIntegratedRepositoryTest<DiscussionReply, DiscussionReplyRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        DiscussionReply reply = createDiscussionReply();
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(reply));
    }

    @Test
    public void withMockUser() throws Exception {
        DiscussionReply reply = createDiscussionReply();
        DiscussionReply entity = (DiscussionReply) withMockUser(() -> save(reply));
        DiscussionReply result = (DiscussionReply) withMockUser(() -> findOne(entity.getId()));
        Assert.assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeUser() throws Exception {
        DiscussionReply reply = createDiscussionReply();
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeUser(() -> save(reply));
    }

    @Test
    public void withMockInstructor() throws Exception {
        DiscussionReply reply = createDiscussionReply();
        DiscussionReply entity = (DiscussionReply) withMockInstructor(() -> save(reply));
        DiscussionReply result = (DiscussionReply) withMockInstructor(() -> findOne(entity.getId()));
        Assert.assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        DiscussionReply reply = createDiscussionReply();
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> save(reply));
    }
}
