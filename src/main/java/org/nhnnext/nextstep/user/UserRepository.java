package org.nhnnext.nextstep.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<User> findByUsername(String username);
}
