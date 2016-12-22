package org.nhnnext.nextstep.discussion;

import org.nhnnext.nextstep.user.UserExcerpt;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "excerpt", types = { Discussion.class, DiscussionReply.class })
public interface DiscussionExcerpt {

    Long getId();

    String getComment();

    UserExcerpt getCreatedBy();

    LocalDateTime getCreatedDate();

    LocalDateTime getLastModifiedDate();
}