package org.nhnnext.nextstep.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.nextstep.core.domain.AbstractAuditingEntity;
import org.nhnnext.nextstep.core.domain.acls.AclImpl;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.session.MasterSession;
import org.nhnnext.nextstep.session.Session;
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

import javax.persistence.*;
import java.util.*;

//@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Course extends AbstractAuditingEntity<User, Long> {

    public Course() {
        addToSessions(new MasterSession());
        addToSessions(new CourseSession("default"));
    }

    @NotEmpty
    private String name;

    private String description;

//    @Column(unique = true)
//    @ManyToMany
//    private final List<Instructor> instructors = new ArrayList<>();

    public List<Instructor> getInstructors() {
        if (getCreatedBy() == null) {
            return Collections.unmodifiableList(Collections.EMPTY_LIST);
        }

        return Collections.unmodifiableList(Collections.singletonList((Instructor) getCreatedBy()));
    }

//    @Column(unique = true)
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", orphanRemoval = true)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "course")//(mappedBy = "course", fetch = FetchType.LAZY)
//    @Cascade(CascadeType.ALL)
    private final List<Session> sessions = new ArrayList<>();

    public Optional<Session> getSession(String name) {
        return getSessions().stream().filter(x -> Objects.equals(x.getName(), name)).findFirst();
    }

    public void addToSessions(Session session) {
        getSessions().add(session);
        session.setCourse(this);
    }

    //    @OneToOne(cascade = CascadeType.ALL, mappedBy = "course")
//    private MasterSession masterSession;

    public boolean isInstructor(Authentication authentication) {
        return getInstructors().contains(AuthenticationUtils.getUser(authentication));
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
        acl.insertAce(acl.getEntries().size(), BasePermission.CREATE, new GrantedAuthoritySid(GrantedAuthorities.ROLE_INSTRUCTOR), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.WRITE, new GrantedAuthoritySid(GrantedAuthorities.COURSE_INSTRUCTOR), true);
        acl.insertAce(acl.getEntries().size(), BasePermission.DELETE, new GrantedAuthoritySid(GrantedAuthorities.COURSE_INSTRUCTOR), true);
        return acl;
    }
}
