package org.nhnnext.nextstep.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InstructorRepository extends CrudRepository<Instructor, Long> {

    Optional<Instructor> findByUsername(@Param("username") String username);
}
