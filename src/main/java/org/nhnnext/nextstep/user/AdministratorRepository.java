package org.nhnnext.nextstep.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdministratorRepository extends CrudRepository<Administrator, Long> {

    Optional<Administrator> findByUsername(String username);
}
