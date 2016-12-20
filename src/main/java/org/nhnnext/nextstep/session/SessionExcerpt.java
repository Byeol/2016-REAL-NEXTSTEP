package org.nhnnext.nextstep.session;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = Session.class)
public interface SessionExcerpt {

    Long getId();

    String getName();

    String getDescription();
}
