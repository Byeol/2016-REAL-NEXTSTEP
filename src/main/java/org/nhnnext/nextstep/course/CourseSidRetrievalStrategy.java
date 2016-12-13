package org.nhnnext.nextstep.course;

import org.nhnnext.nextstep.core.domain.acls.SidRetrievalStrategy;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseSidRetrievalStrategy implements SidRetrievalStrategy {

    @Override
    public List<Sid> getSids(Authentication authentication) {
        return null;
    }
}
