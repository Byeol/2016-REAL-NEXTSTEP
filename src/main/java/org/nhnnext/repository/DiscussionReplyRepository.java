package org.nhnnext.repository;

import org.nhnnext.domain.DiscussionReply;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface DiscussionReplyRepository extends CrudRepository<DiscussionReply, Long> {
}
