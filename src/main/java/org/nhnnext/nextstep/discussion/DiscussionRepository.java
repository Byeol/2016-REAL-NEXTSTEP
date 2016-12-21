package org.nhnnext.nextstep.discussion;

import org.nhnnext.nextstep.core.repository.AuditingRepository;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.lesson.LessonExcerpt;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = DiscussionExcerpt.class)
public interface DiscussionRepository extends AuditingRepository<Discussion, Long> {
}
