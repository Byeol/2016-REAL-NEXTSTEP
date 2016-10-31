package org.nhnnext.domain;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "excerpt", types = Issue.class)
public interface IssueExcerpt {

	String getTitle();

	String getContent();
}
