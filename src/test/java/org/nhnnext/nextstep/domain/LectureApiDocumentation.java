//package org.nhnnext.nextstep.domain;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.nhnnext.nextstep.domain.Course;
//import org.nhnnext.nextstep.domain.Lecture;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.restdocs.payload.JsonFieldType;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.startsWith;
//import static org.nhnnext.nextstep.domain.CourseApiDocumentation.API_URL_COURSES;
//import static org.nhnnext.nextstep.domain.CourseApiDocumentation.getSampleCourse;
//import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
//import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class LectureApiDocumentation extends AbstractApiDocumentation {
//
//	static final String API_URL_LECTURES = "/api/lectures";
//
//	@Autowired
//	private CourseRepository courseRepository;
//
//	@Autowired
//	private LectureRepository lectureRepository;
//
//	@Before
//	public void initialize() {
//		lectureRepository.deleteAll();
//		courseRepository.deleteAll();
//	}
//
//	@Test
//	public void courseLecturesListExample() throws Exception {
//		Course course = createCourse("Machine Learning", "Machine learning is the science of getting computers to act without being explicitly programmed.");
//
//		createLecture("Welcome to Machine Learning!", course);
//		createLecture("Supervised Learning", course);
//		createLecture("Unsupervised Learning", course);
//
//		performGet(API_URL_COURSES + "/" + course.getId() + "/lectures")
//				.andDo(
//						document("course-lectures-list-example",
//								links(
//										linkWithRel("self").description("Canonical link for this resource")
//								),
//								responseFields(
//										fieldWithPath("_embedded.lectures").description("An array of <<resources-lecture, Lecture resources>>"),
//										fieldWithPath("_links").description("<<resources-tags-list-links, Links>> to other resources")
//								)
//						)
//				);
//	}
//
//	@Test
//	public void lecturesCreateExample() throws Exception {
//		Map<String, Object> course = getSampleCourse();
//		String courseLocation = getLocation(API_URL_COURSES, course);
//
//		Map<String, Object> lecture = getSampleLecture(courseLocation);
//
//		performPost(API_URL_LECTURES, lecture)
//				.andDo(
//						document("lectures-create-example",
//								requestFields(
//										fieldWithPath("title").description("The title of the lecture"),
//										fieldWithPath("course").description("The course of the lecture")
//								)
//						)
//				);
//	}
//
//	@Test
//	public void lectureGetExample() throws Exception {
//		Map<String, Object> course = getSampleCourse();
//		String courseLocation = getLocation(API_URL_COURSES, course);
//
//		Map<String, Object> lecture = getSampleLecture(courseLocation);
//		String lectureLocation = getLocation(API_URL_LECTURES, lecture);
//
//		performGet(lectureLocation)
//				.andExpect(jsonPath("title", is(lecture.get("title"))))
//				.andExpect(jsonPath("_links.self.href", is(lectureLocation)))
//				.andExpect(jsonPath("_embedded.course._links.self.href", startsWith(courseLocation)))
//				.andDo(
//						document("lecture-get-example",
//								links(
//										linkWithRel("self").description("Canonical link for this <<resources-lecture,lecture>>"),
//										linkWithRel("lecture").description("This <<resources-lecture,lecture>>"),
//										linkWithRel("course").description("The <<resources-course,course>> that have this lecture"),
//										linkWithRel("issues").description("This lecture's issues")
//								),
//								responseFields(
//										fieldWithPath("title").description("The title of the lecture"),
//										fieldWithPath("_embedded.course").description("The <<resources-course,course>> that have this lecture"),
//										fieldWithPath("_links").description("<<resources-lecture-links,Links>> to other resources")
//								)
//						)
//				);
//
//		performGet(courseLocation + "/lectures")
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("_embedded.lectures[0]._links.self.href", is(lectureLocation)));
//	}
//
//	@Test
//	public void lectureUpdateExample() throws Exception {
//		Map<String, Object> course = getSampleCourse();
//		String courseLocation = getLocation(API_URL_COURSES, course);
//
//		Map<String, Object> lecture = getSampleLecture(courseLocation);
//		String lectureLocation = getLocation(API_URL_LECTURES, lecture);
//
//		performGet(lectureLocation)
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("title", is(lecture.get("title"))))
//				.andExpect(jsonPath("_links.self.href", is(lectureLocation)))
//				.andExpect(jsonPath("_embedded.course._links.self.href", startsWith(courseLocation)));
//
//		Map<String, Object> lectureUpdate = new HashMap<>();
//		lectureUpdate.put("title", "Supervised Learning");
//
//		performPatch(lectureLocation, lectureUpdate)
//				.andDo(
//						document("lecture-update-example",
//								requestFields(
//										fieldWithPath("title").description("The title of the lecture").type(JsonFieldType.STRING).optional()
//								)
//						)
//				);
//
//		performGet(lectureLocation)
//				.andExpect(jsonPath("title", is(lectureUpdate.get("title"))))
//				.andExpect(jsonPath("_links.self.href", is(lectureLocation)));
//	}
//
//	static Map<String, Object> getSampleLecture(String courseLocation) {
//		Map<String, Object> lecture = new HashMap<>();
//		lecture.put("title", "Welcome to Machine Learning!");
//		lecture.put("course", courseLocation);
//		return lecture;
//	}
//
//	private Course createCourse(String name, String description) {
//		Course course = new Course();
//		course.setName(name);
//		course.setDescription(description);
//
//		return courseRepository.save(course);
//	}
//
//	private Lecture createLecture(String title, Course course) {
//		Lecture lecture = new Lecture();
//		lecture.setTitle(title);
//		lecture.setCourse(course);
//
//		return lectureRepository.save(lecture);
//	}
//}
