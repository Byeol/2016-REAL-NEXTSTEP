package org.nhnnext.nextstep.session;

import org.nhnnext.nextstep.lecture.LectureExcerpt;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "detail", types = Session.class)
public interface SessionDetail {

    String getName();

    String getDescription();

    List<LectureExcerpt> getLectures();

    Object getLecturePos();
}