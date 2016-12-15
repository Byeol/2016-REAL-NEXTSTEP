package org.nhnnext.nextstep.session;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.nhnnext.nextstep.core.domain.AbstractAuditingEntity;
import org.nhnnext.nextstep.core.domain.AbstractEntity;
import org.nhnnext.nextstep.core.domain.acls.AclImpl;
import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.user.AuthenticationUtils;
import org.nhnnext.nextstep.user.GrantedAuthorities;
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
import java.util.List;

//@NoArgsConstructor(force = true)
@Data
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Session extends AbstractAuditingEntity<User, Long> {

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false) //(fetch = FetchType.EAGER) //(cascade = CascadeType.PERSIST)//(optional = false)
//    @Cascade(CascadeType.ALL)
//    @JoinColumn(name="COURSE_ID")
    private Course course;


    public boolean isInstructor(Authentication authentication) {
        Assert.notNull(getCourse());
        return getCourse().isInstructor(authentication);
    }

    @JsonIgnore
    @Transient
    public List<Sid> getSids(Authentication authentication) {
        List<Sid> sids = new ArrayList<>();

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
        acl.insertAce(acl.getEntries().size(), BasePermission.CREATE, new GrantedAuthoritySid(GrantedAuthorities.COURSE_INSTRUCTOR), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.WRITE, new GrantedAuthoritySid(GrantedAuthorities.COURSE_INSTRUCTOR), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.DELETE, new GrantedAuthoritySid(GrantedAuthorities.COURSE_INSTRUCTOR), true);
        return acl;
    }
}
