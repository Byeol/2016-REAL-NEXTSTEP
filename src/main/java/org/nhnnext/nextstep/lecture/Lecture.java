package org.nhnnext.nextstep.lecture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.nextstep.core.ObjectConverter;
import org.nhnnext.nextstep.core.domain.AbstractAuditingEntity;
import org.nhnnext.nextstep.core.domain.AbstractEntity;
import org.nhnnext.nextstep.core.domain.acls.AclImpl;
import org.nhnnext.nextstep.course.domain.AbstractCourseEntity;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.session.Session;
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

@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class Lecture extends AbstractCourseEntity {

    @Convert(converter = ObjectConverter.class)
    private Object pos = new ArrayList<>();

    @NotEmpty
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Session session;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "lecture")//(mappedBy = "course", fetch = FetchType.LAZY)
//    @Cascade(CascadeType.ALL)
    private final List<Lesson> lessons = new ArrayList<>();

    public void addToLessons(Lesson lesson) {
        getLessons().add(lesson);
        lesson.setLecture(this);
    }

    public boolean isInstructor(Authentication authentication) {
        Assert.notNull(getSession());
        return getSession().isInstructor(authentication);
    }
}
