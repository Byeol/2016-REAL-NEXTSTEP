package org.nhnnext.repository;

import org.nhnnext.domain.Lesson;
import org.nhnnext.domain.LessonExcerpt;
import org.nhnnext.domain.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface SessionRepository extends CrudRepository<Session, Long> {
}

