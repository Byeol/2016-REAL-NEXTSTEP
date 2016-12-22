package org.nhnnext.nextstep.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdministratorRepository extends CrudRepository<Administrator, Long> {

    Optional<Administrator> findByUsername(@Param("username") String username);
}
