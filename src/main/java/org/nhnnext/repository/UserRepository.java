package org.nhnnext.repository;

import org.nhnnext.domain.User;
import org.nhnnext.domain.UserExcerpt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = UserExcerpt.class)
public interface UserRepository extends CrudRepository<User, Long> {
}
