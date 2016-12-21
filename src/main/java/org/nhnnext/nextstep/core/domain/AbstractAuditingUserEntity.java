package org.nhnnext.nextstep.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.nhnnext.nextstep.user.AuthenticationUtils;
import org.nhnnext.nextstep.user.GrantedAuthorities;
import org.nhnnext.nextstep.user.User;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractAuditingUserEntity<PK extends Serializable> extends AbstractAuditingEntity<User, PK> {

    @JsonIgnore
    @Transient
    public boolean isCreatedBy(Authentication authentication) {
        return isCreatedBy(AuthenticationUtils.getUser(authentication));
    }

    @JsonIgnore
    @Transient
    public List<Sid> getSids(Authentication authentication) {
        List<Sid> sids = new ArrayList<>();

        if (isCreatedBy(authentication)) {
            sids.add(new GrantedAuthoritySid(GrantedAuthorities.ENTITY_OWNER));
        }

        return sids;
    }
}
