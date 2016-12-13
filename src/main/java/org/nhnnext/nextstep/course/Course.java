package org.nhnnext.nextstep.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.nhnnext.nextstep.core.domain.AbstractAuditable;
import org.nhnnext.nextstep.core.domain.AbstractAuditingEntity;
import org.nhnnext.nextstep.core.domain.AbstractEntity;
import org.nhnnext.nextstep.core.domain.AuditingEntity;
import org.nhnnext.nextstep.core.domain.acls.AclImpl;
import org.nhnnext.nextstep.core.domain.acls.SidRetrievalStrategy;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.user.Instructor;
import org.nhnnext.nextstep.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.*;

//@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(of = "name")
@Entity
public class Course extends AbstractAuditingEntity<User, Long> {

    //    @NotEmpty
    private String name;

    private String description;

//    @Column(unique = true)
//    @ManyToMany
//    private final List<Instructor> instructors = new ArrayList<>();

    public List<Instructor> getInstructors() {
        System.out.println(getCreatedBy());

        if (getCreatedBy() == null) {
            return Collections.unmodifiableList(Collections.EMPTY_LIST);
        }

        return Collections.unmodifiableList(Collections.singletonList((Instructor) getCreatedBy()));
    }

//    @Column(unique = true)
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", orphanRemoval = true)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "course")//(mappedBy = "course", fetch = FetchType.LAZY)
//    @Cascade(CascadeType.ALL)
    private final List<CourseSession> sessions = new ArrayList<>();

    public void addToSessions(CourseSession session) {
        this.getSessions().add(session);
        session.setCourse(this);
    }

    //    @OneToOne(cascade = CascadeType.ALL, mappedBy = "course")
//    private MasterSession masterSession;

    public boolean isInstructor(Authentication authentication) {
//        return getInstructors().contains(authentication);
        if (authentication == null) {
            return false;
        }

        System.out.println(getInstructors());
        return getInstructors().stream().anyMatch(x -> x.getName() == authentication.getName());
//        return false;
    }

    @JsonIgnore
    @Transient
    public List<Sid> getSids(Authentication authentication) {
        List<Sid> sids = new ArrayList<>();

        if (isInstructor(authentication)) {
            sids.add(new GrantedAuthoritySid("COURSE_INSTRUCTOR"));
        }

        return sids;
    }

    @JsonIgnore
    @Transient
    public Acl getAcl() {
        MutableAcl acl = new AclImpl();
        acl.insertAce(acl.getEntries().size(), BasePermission.READ, new GrantedAuthoritySid("ROLE_GUEST"), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.CREATE, new GrantedAuthoritySid("ROLE_INSTRUCTOR"), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.WRITE, new GrantedAuthoritySid("COURSE_INSTRUCTOR"), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.DELETE, new GrantedAuthoritySid("COURSE_INSTRUCTOR"), true);
        return acl;
    }
}
