package org.nhnnext.nextstep.discussion;

import lombok.Getter;
import lombok.Setter;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.user.UserExcerpt;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Projection(name = "excerpt", types = Discussion.class)
public interface DiscussionExcerpt {

    Long getId();

    String getComment();

    UserExcerpt getCreatedBy();

    LocalDateTime getCreatedDate();

    LocalDateTime getLastModifiedDate();
}