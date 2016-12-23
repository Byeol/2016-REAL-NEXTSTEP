package org.nhnnext.nextstep.core.web;

import lombok.RequiredArgsConstructor;
import org.nhnnext.nextstep.core.domain.AbstractAuditingUserEntity;
import org.nhnnext.nextstep.lesson.Lesson;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AuditingController<T extends AbstractAuditingUserEntity> {

    @GetMapping("/authorities")
    Map<String, Object> getAuthorities(@PathVariable("id") T entity, Authentication authentication) {
        Map<String, Object> map = new HashMap<>();
        List<Sid> sids = entity.getSids(authentication);
        map.put("authorities", sids);
        return map;
    }
}
