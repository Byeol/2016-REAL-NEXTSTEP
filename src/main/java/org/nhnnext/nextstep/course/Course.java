package org.nhnnext.nextstep.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.nextstep.core.domain.acls.AclImpl;
import org.nhnnext.nextstep.course.domain.AbstractCourseEntity;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.session.MasterSession;
import org.nhnnext.nextstep.session.Session;
import org.nhnnext.nextstep.user.AuthenticationUtils;
import org.nhnnext.nextstep.user.GrantedAuthorities;
import org.nhnnext.nextstep.user.Instructor;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.core.Authentication;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Course extends AbstractCourseEntity {

    public Course() {
        addToSessions(new MasterSession());
        addToSessions(new CourseSession("Default Session"));
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

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private final List<Session> sessions = new ArrayList<>();

    @Transient
    public List<Session> getCourseSessions() {
        return this.sessions.stream().filter(x -> x instanceof CourseSession).collect(Collectors.toList());
    }

    @Transient
    @JsonIgnore
    public Session getMasterSession() {
        return this.sessions.stream().filter(x -> x instanceof MasterSession).findFirst().orElseGet(null);
    }

    @Transient
    @JsonIgnore
    public Session getDefaultSession() {
        return this.sessions.stream().filter(x -> x instanceof CourseSession).findFirst().orElseGet(null);
    }

    public void addToSessions(Session session) {
        getSessions().add(session);
        session.setCourse(this);
    }

    public boolean isInstructor(Authentication authentication) {
        return getInstructors().contains(AuthenticationUtils.getUser(authentication));
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
