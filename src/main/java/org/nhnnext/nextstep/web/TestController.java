//package org.nhnnext.nextstep.web;
//
//import lombok.RequiredArgsConstructor;
//import org.nhnnext.nextstep.domain.SecurityUser;
//import org.nhnnext.nextstep.repository.old.*;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//public class TestController {
//
//	private final UserRepository userRepository;
//	private final LectureRepository lectureRepository;
//	private final CourseRepository courseRepository;
//	private final LessonRepository lessonRepository;
//
//	private final SecurityUserRepository securityUserRepository;
//
////	@PreAuthorize("isAuthenticated()")
//	@GetMapping("/user")
//	public Object getAuthenticatedUser(@AuthenticationPrincipal Object principal) {
//		return principal;
//	}
//
//	@GetMapping("/test")
//	public String createTestData() {
//		SecurityUser user = new SecurityUser("testuser", "password");
//		securityUserRepository.save(user);
//
//		SecurityUser user2 = new SecurityUser("teacher", "password", "ROLE_INSTRUCTOR");
//		securityUserRepository.save(user2);
//
//		SecurityUser user3 = new SecurityUser("teacher2", "testpass", "ROLE_INSTRUCTOR");
//		securityUserRepository.save(user3);
//
//		return "ok!";
//
////		User student = new User();
////		student.setName("학생 1");
////		userRepository.save(student);
////
////		User professor = new User();
////		professor.setName("교수 1");
////		userRepository.save(professor);
////
////		Course course = new Course();
////		course.setName("강의 1");
////		course.setInstructors(new HashSet<>());
////		course.getInstructors().add(professor);
////		course.getInstructors().add(professor);
////		course.setParticipants(new HashSet<>());
////		course.getParticipants().add(student);
////		course.getParticipants().add(student);
////		course.setLectures(new ArrayList<>());
////		courseRepository.save(course);
////
////		Lecture lecture = new Lecture();
////		lecture.setTitle("수업 1");
//////		course.setLecture(lecture);
////		course.getLectures().add(lecture);
////		lecture.setIssues(new ArrayList<>());
////		courseRepository.save(course);
////		lectureRepository.save(lecture);
////
////		Issue issue = new Issue();
////		issue.setTitle("학습목표 1");
//////		issue.setCourse(course);
////		lecture.getIssues().add(issue);
////		issueRepository.save(issue);
////		lectureRepository.save(lecture);
////
////		issue = new Issue();
////		issue.setTitle("학습목표 2");
//////		issue.setCourse(course);
////		lecture.getIssues().add(issue);
////		issueRepository.save(issue);
////		lectureRepository.save(lecture);
////
////		issue = new Issue();
////		issue.setTitle("학습목표 3");
//////		issue.setCourse(course);
////		lecture.getIssues().add(issue);
////		issueRepository.save(issue);
////		lectureRepository.save(lecture);
////
////		course = new Course();
////		lecture.setTitle("수업 2");
//////		course.setLecture(lecture);
////		course.getLectures().add(lecture);
////		lecture.setIssues(new ArrayList<>());
////		courseRepository.save(course);
////		lectureRepository.save(lecture);
////
////		course = new Course();
////		lecture.setTitle("수업 3");
//////		course.setLecture(lecture);
////		course.getLectures().add(lecture);
////		lecture.setIssues(new ArrayList<>());
////		courseRepository.save(course);
////		lectureRepository.save(lecture);
////
////		return "ok!";
//	}
//}
