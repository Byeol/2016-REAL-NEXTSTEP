package org.nhnnext.nextstep.discussion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.nextstep.discussion.domain.AbstractDiscussionEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class DiscussionReply extends AbstractDiscussionEntity {

    @NotEmpty
    private String comment;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Discussion discussion;

    public boolean isPublic() {
        Assert.notNull(getDiscussion());
        return getDiscussion().isPublic();
    }

    public boolean isInstructor(Authentication authentication) {
        Assert.notNull(getDiscussion());
        return getDiscussion().isInstructor(authentication);
    }

    public boolean isParticipant(Authentication authentication) {
        Assert.notNull(getDiscussion());
        return getDiscussion().isParticipant(authentication);
    }
}
