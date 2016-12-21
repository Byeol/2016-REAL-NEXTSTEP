package org.nhnnext.nextstep.lesson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.nextstep.core.ObjectConverter;
import org.nhnnext.nextstep.core.domain.acls.AclImpl;
import org.nhnnext.nextstep.course.domain.AbstractCourseEntity;
import org.nhnnext.nextstep.discussion.Discussion;
import org.nhnnext.nextstep.lecture.Lecture;
import org.nhnnext.nextstep.session.domain.AbstractCourseSessionEntity;
import org.nhnnext.nextstep.user.GrantedAuthorities;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class Lesson extends AbstractCourseSessionEntity {

    @NotEmpty
    private String name;

    private String content;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Lecture lecture;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    private final List<Discussion> discussions = new ArrayList<>();

    public void addToDiscussions(Discussion discussion) {
        getDiscussions().add(discussion);
        discussion.setLesson(this);
    }
    @NotNull
    private Access access = Access.PRIVATE;

    public enum Access {
        PUBLIC,
        PRIVATE
    }

    public boolean isInstructor(Authentication authentication) {
        Assert.notNull(getLecture());
        return getLecture().isInstructor(authentication);
    }

    @Override
    public boolean isParticipant(Authentication authentication) {
        Assert.notNull(getLecture());
        return getLecture().isParticipant(authentication);
    }
}
