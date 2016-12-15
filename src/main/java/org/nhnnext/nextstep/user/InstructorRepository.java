package org.nhnnext.nextstep.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InstructorRepository extends CrudRepository<Instructor, Long> {
    Optional<Instructor> findByUsername(String username);
}
