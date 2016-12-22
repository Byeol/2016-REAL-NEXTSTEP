package org.nhnnext.nextstep.user;

import org.nhnnext.nextstep.core.repository.AuditingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RepositoryRestResource(excerptProjection = UserExcerpt.class)
public interface UserRepository extends AuditingRepository<User, Long> {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<User> findByUsername(String username);
}
