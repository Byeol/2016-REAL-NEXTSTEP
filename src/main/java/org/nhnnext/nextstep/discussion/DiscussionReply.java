package org.nhnnext.nextstep.discussion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.nextstep.core.domain.acls.AclImpl;
import org.nhnnext.nextstep.discussion.domain.AbstractDiscussionEntity;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.session.domain.AbstractCourseSessionEntity;
import org.nhnnext.nextstep.user.GrantedAuthorities;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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

    public boolean isInstructor(Authentication authentication) {
        Assert.notNull(getDiscussion());
        return getDiscussion().isInstructor(authentication);
    }

    public boolean isParticipant(Authentication authentication) {
        Assert.notNull(getDiscussion());
        return getDiscussion().isParticipant(authentication);
    }
}
