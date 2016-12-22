package org.nhnnext.nextstep.discussion;

import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.user.UserExcerpt;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = Discussion.class)
public interface DiscussionExcerpt {

    String getComment();

    UserExcerpt getCreatedBy();
}