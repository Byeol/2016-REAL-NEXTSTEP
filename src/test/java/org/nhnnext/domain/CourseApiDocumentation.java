package org.nhnnext.domain;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CourseApiDocumentation extends AbstractApiDocumentation {

	static final String API_URL_COURSES = "/api/courses";

	@Autowired
	private CourseRepository courseRepository;

	@Before
	public void initialize() {
		courseRepository.deleteAll();
	}

	@Test
	public void coursesListExample() throws Exception {
		createCourse("Course 1", "This is the first course.");
		createCourse("Course 2", "This is the second course.");

		performGet(API_URL_COURSES)
				.andDo(
						document("courses-list-example",
								links(
										linkWithRel("self").description("Canonical link for this resource"),
										linkWithRel("profile").description("The ALPS profile for this resource")
								),
								responseFields(
										fieldWithPath("_embedded.courses").description("An array of <<resources-course, Course resources>>"),
										fieldWithPath("_links").description("<<resources-tags-list-links, Links>> to other resources")
								)
						)
				);
	}

	@Test
	public void coursesCreateExample() throws Exception {
		Map<String, Object> course = getSampleCourse();

		performPost(API_URL_COURSES, course)
				.andDo(
						document("courses-create-example",
								requestFields(
										fieldWithPath("name").description("The name of the course"),
										fieldWithPath("description").description("The description of the course")
								)
						)
				);
	}

	@Test
	public void courseGetExample() throws Exception {
		Map<String, Object> course = getSampleCourse();
		String courseLocation = getLocation(API_URL_COURSES, course);

		performGet(courseLocation)
				.andExpect(jsonPath("name", is(course.get("name"))))
				.andExpect(jsonPath("description", is(course.get("description"))))
				.andExpect(jsonPath("_links.self.href", is(courseLocation)))
				.andDo(
						document("course-get-example",
								links(
										linkWithRel("self").description("Canonical link for this <<resources-course,course>>"),
										linkWithRel("course").description("This <<resources-course,course>>"),
										linkWithRel("lectures").description("This <<resources-lectures,lectures>>"),
										linkWithRel("instructors").description("This <<resources-instructors,instructors>>"),
										linkWithRel("participants").description("This <<resources-participants,participants>>")
								),
								responseFields(
										fieldWithPath("name").description("The name of the course"),
										fieldWithPath("description").description("The description of the course"),
										fieldWithPath("state").description("The state of the course"),
										fieldWithPath("_links").description("<<resources-note-links,Links>> to other resources")
								)
						)
				);
	}

	@Test
	public void courseUpdateExample() throws Exception {
		Map<String, Object> course = getSampleCourse();
		String courseLocation = getLocation(API_URL_COURSES, course);

		performGet(courseLocation)
				.andExpect(jsonPath("name", is(course.get("name"))))
				.andExpect(jsonPath("description", is(course.get("description"))))
				.andExpect(jsonPath("_links.self.href", is(courseLocation)));

		Map<String, Object> courseUpdate = new HashMap<>();
		courseUpdate.put("name", "Neural Networks for Machine Learning");
		courseUpdate.put("description", "Learn about artificial neural networks and how they're being used for machine learning, as applied to speech and object recognition, image segmentation, modeling language and human motion, etc.");

		performPatch(courseLocation, courseUpdate)
				.andDo(
						document("course-update-example",
								requestFields(
										fieldWithPath("name").description("The name of the course").type(JsonFieldType.STRING).optional(),
										fieldWithPath("description").description("The description of the course").type(JsonFieldType.STRING).optional()
								)
						)
				);

		performGet(courseLocation)
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", is(courseUpdate.get("name"))))
				.andExpect(jsonPath("description", is(courseUpdate.get("description"))))
				.andExpect(jsonPath("_links.self.href", is(courseLocation)));
	}

	static Map<String, Object> getSampleCourse() {
		Map<String, Object> course = new HashMap<>();
		course.put("name", "Machine Learning");
		course.put("description", "Machine learning is the science of getting computers to act without being explicitly programmed.");
		return course;
	}

	private void createCourse(String name, String description) {
		Course course = new Course();
		course.setName(name);
		course.setDescription(description);

		courseRepository.save(course);
	}
}
