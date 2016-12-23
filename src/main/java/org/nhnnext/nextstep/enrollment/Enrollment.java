package org.nhnnext.nextstep.enrollment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.nhnnext.nextstep.core.domain.acls.AclImpl;
import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.course.domain.AbstractCourseEntity;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.user.AuthenticationUtils;
import org.nhnnext.nextstep.user.GrantedAuthorities;
import org.nhnnext.nextstep.user.User;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.Objects;

@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class Enrollment extends AbstractCourseEntity {

    @Transient
    public User getUser() {
        return getCreatedBy();
    }

    @ManyToOne(optional = false)
    private CourseSession session;

//    @ManyToOne(optional = false)
//    private final User user;

    private Status status = Status.PENDING;

    public static enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    @Transient
    public Course getCourse() {
        return getSession().getCourse();
    }

    public boolean isApproved() {
        return Enrollment.Status.APPROVED.equals(this.status);
    }

    public boolean isCreatedBy(Authentication authentication) {
        return Objects.equals(getCreatedBy(), AuthenticationUtils.getUser(authentication));
    }

    public boolean isInstructor(Authentication authentication) {
        Assert.notNull(getSession());
        return getSession().isInstructor(authentication);
    }

    @JsonIgnore
    @Transient
    public Acl getAcl() {
        MutableAcl acl = new AclImpl();
        acl.insertAce(acl.getEntries().size(), BasePermission.READ, new GrantedAuthoritySid(GrantedAuthorities.ROLE_ANONYMOUS), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.CREATE, new GrantedAuthoritySid(GrantedAuthorities.ROLE_USER), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.WRITE, new GrantedAuthoritySid(GrantedAuthorities.COURSE_INSTRUCTOR), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.DELETE, new GrantedAuthoritySid(GrantedAuthorities.ENTITY_OWNER), true);
        return acl;
    }
}
