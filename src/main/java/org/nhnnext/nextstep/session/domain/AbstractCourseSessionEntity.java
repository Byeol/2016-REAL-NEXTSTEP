package org.nhnnext.nextstep.session.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.nhnnext.nextstep.course.domain.AbstractCourseEntity;
import org.nhnnext.nextstep.user.GrantedAuthorities;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.List;

@MappedSuperclass
public abstract class AbstractCourseSessionEntity extends AbstractCourseEntity implements CourseSessionEntity {

    @JsonIgnore
    @Transient
    public List<Sid> getSids(Authentication authentication) {
        List<Sid> sids = super.getSids(authentication);

        if (isParticipant(authentication)) {
            sids.add(new GrantedAuthoritySid(GrantedAuthorities.COURSE_PARTICIPANT));
        }

        return sids;
    }
}
