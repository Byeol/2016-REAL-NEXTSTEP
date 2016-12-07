package org.nhnnext.repository;

import org.nhnnext.domain.MyUser;
import org.nhnnext.domain.SecurityUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface MyUserRepository extends CrudRepository<MyUser, Long> {
    Optional<MyUser> findByAuthId(Integer authId);
}
