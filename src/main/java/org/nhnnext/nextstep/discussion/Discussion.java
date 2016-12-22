package org.nhnnext.nextstep.discussion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.nextstep.discussion.domain.AbstractDiscussionEntity;
import org.nhnnext.nextstep.lesson.Lesson;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class Discussion extends AbstractDiscussionEntity {

    @NotEmpty
    private String comment;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Lesson lesson;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discussion")
    private final List<DiscussionReply> replies = new ArrayList<>();

    public void addToReplies(DiscussionReply reply) {
        getReplies().add(reply);
        reply.setDiscussion(this);
    }

    public boolean isPublic() {
        Assert.notNull(getLesson());
        return getLesson().isPublic();
    }

    public boolean isInstructor(Authentication authentication) {
        Assert.notNull(getLesson());
        return getLesson().isInstructor(authentication);
    }

    public boolean isParticipant(Authentication authentication) {
        Assert.notNull(getLesson());
        return getLesson().isParticipant(authentication);
    }
}
