package org.nhnnext.nextstep.enrollment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.nhnnext.nextstep.core.domain.AbstractAuditingEntity;
import org.nhnnext.nextstep.core.domain.AbstractEntity;
import org.nhnnext.nextstep.core.domain.acls.AclImpl;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.user.AuthenticationUtils;
import org.nhnnext.nextstep.user.GrantedAuthorities;
import org.nhnnext.nextstep.user.Instructor;
import org.nhnnext.nextstep.user.User;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(force = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@ToString(callSuper = true)
public class Enrollment extends AbstractAuditingEntity<User, Long> {

    @Transient
    public User getUser() {
        return getCreatedBy();
    }

    @ManyToOne(optional = false)
    private CourseSession session;

//    @ManyToOne(optional = false)
//    private final User user;

//    public Enrollment(CourseSession session, User user) {
//        this.session = session;
//        this.user = user;
//        this.status = Status.PENDING;
//    }
//
//    public Enrollment() {
//        this(null, null);
//    }

    private Status status = Status.PENDING;

    public static enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    public boolean isApproved() {
        return Enrollment.Status.APPROVED.equals(this.status);
    }

    public boolean isCreatedBy(Authentication authentication) {
        return getCreatedBy().equals(AuthenticationUtils.getUser(authentication));
    }

    public boolean isInstructor(Authentication authentication) {
        Assert.notNull(getSession());
        return getSession().isInstructor(authentication);
    }

    @JsonIgnore
    @Transient
    public List<Sid> getSids(Authentication authentication) {
        List<Sid> sids = new ArrayList<>();

        if (isCreatedBy(authentication)) {
            sids.add(new GrantedAuthoritySid(GrantedAuthorities.ENROLLMENT_USER));
        }

        if (isInstructor(authentication)) {
            sids.add(new GrantedAuthoritySid(GrantedAuthorities.COURSE_INSTRUCTOR));
        }

        return sids;
    }

    @JsonIgnore
    @Transient
    public Acl getAcl() {
        MutableAcl acl = new AclImpl();
        acl.insertAce(acl.getEntries().size(), BasePermission.READ, new GrantedAuthoritySid(GrantedAuthorities.ROLE_ANONYMOUS), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.CREATE, new GrantedAuthoritySid(GrantedAuthorities.ROLE_USER), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.WRITE, new GrantedAuthoritySid(GrantedAuthorities.COURSE_INSTRUCTOR), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.DELETE, new GrantedAuthoritySid(GrantedAuthorities.ENROLLMENT_USER), true);
        return acl;
    }
}
