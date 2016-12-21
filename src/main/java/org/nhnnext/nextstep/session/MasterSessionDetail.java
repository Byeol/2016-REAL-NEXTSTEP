package org.nhnnext.nextstep.session;

import org.nhnnext.nextstep.lecture.LectureExcerpt;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "detail", types = MasterSession.class)
public interface MasterSessionDetail {

    Long getId();

    List<LectureExcerpt> getLectures();

    Object getPos();
}
