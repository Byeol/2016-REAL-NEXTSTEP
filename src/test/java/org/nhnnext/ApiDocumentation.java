package org.nhnnext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureRestDocs("build/generated-snippets")
@AutoConfigureMockMvc
public class ApiDocumentation {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void indexExample() throws Exception {
		this.mockMvc.perform(get("/api/"))
				.andExpect(status().isOk())
				.andDo(
						document("index-example",
								links(
										linkWithRel("courses").description("The <<resources-courses,Courses resource>>"),
										linkWithRel("lectures").description("The <<resources-lectures,Lectures resource>>"),
										linkWithRel("discussions").description("The <<resources-discussions,Discussions resource>>"),
										linkWithRel("issues").description("The <<resources-issues,Issues resource>>"),
										linkWithRel("users").description("The <<resources-users,Users resource>>"),
										linkWithRel("profile").description("The ALPS profile for the service")),
								responseFields(
										fieldWithPath("_links").description("<<resources-index-links,Links>> to other resources")
								)
						)
				);
	}
}
