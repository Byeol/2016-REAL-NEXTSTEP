package org.nhnnext.nextstep.discussion;

import org.nhnnext.nextstep.core.repository.AuditingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = DiscussionExcerpt.class)
public interface DiscussionReplyRepository extends AuditingRepository<DiscussionReply, Long> {
}
