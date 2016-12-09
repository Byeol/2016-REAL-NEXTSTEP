package org.nhnnext.nextstep.domain;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = User.class)
public interface UserExcerpt {

	Long getId();

	String getName();

	String getAvatarUrl();
}
