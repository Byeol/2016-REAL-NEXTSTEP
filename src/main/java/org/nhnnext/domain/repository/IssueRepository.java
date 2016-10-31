package org.nhnnext.domain.repository;

import org.nhnnext.domain.CourseExcerpt;
import org.nhnnext.domain.Issue;
import org.nhnnext.domain.IssueExcerpt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = IssueExcerpt.class)
public interface IssueRepository extends CrudRepository<Issue, Long> {
}
