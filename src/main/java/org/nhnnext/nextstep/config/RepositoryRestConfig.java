package org.nhnnext.nextstep.config;

import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.discussion.Discussion;
import org.nhnnext.nextstep.lecture.Lecture;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.session.MasterSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryRestConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Course.class);
        config.exposeIdsFor(CourseSession.class);
        config.exposeIdsFor(MasterSession.class);
        config.exposeIdsFor(Lecture.class);
        config.exposeIdsFor(Lesson.class);
        config.exposeIdsFor(Discussion.class);
    }
}