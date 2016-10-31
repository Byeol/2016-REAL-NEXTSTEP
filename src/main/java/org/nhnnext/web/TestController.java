//package org.nhnnext.web;
//
//import lombok.RequiredArgsConstructor;
//import org.nhnnext.domain.Course;
//import org.nhnnext.domain.Issue;
//import org.nhnnext.domain.Lecture;
//import org.nhnnext.domain.User;
//import org.nhnnext.domain.repository.CourseRepository;
//import org.nhnnext.domain.repository.IssueRepository;
//import org.nhnnext.domain.repository.LectureRepository;
//import org.nhnnext.domain.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@RestController
//public class TestController {
//
//	private final UserRepository userRepository;
//	private final LectureRepository lectureRepository;
//	private final CourseRepository courseRepository;
//	private final IssueRepository issueRepository;
//
//	@GetMapping("/test")
//	public String createTestData() {
//		User student = new User();
//		student.setName("학생 1");
//		userRepository.save(student);
//
//		User professor = new User();
//		professor.setName("교수 1");
//		userRepository.save(professor);
//
//		Course course = new Course();
//		course.setName("강의 1");
//		course.setInstructors(new HashSet<>());
//		course.getInstructors().add(professor);
//		course.getInstructors().add(professor);
//		course.setParticipants(new HashSet<>());
//		course.getParticipants().add(student);
//		course.getParticipants().add(student);
//		course.setLectures(new ArrayList<>());
//		courseRepository.save(course);
//
//		Lecture lecture = new Lecture();
//		lecture.setTitle("수업 1");
////		course.setLecture(lecture);
//		course.getLectures().add(lecture);
//		lecture.setIssues(new ArrayList<>());
//		courseRepository.save(course);
//		lectureRepository.save(lecture);
//
//		Issue issue = new Issue();
//		issue.setTitle("학습목표 1");
////		issue.setCourse(course);
//		lecture.getIssues().add(issue);
//		issueRepository.save(issue);
//		lectureRepository.save(lecture);
//
//		issue = new Issue();
//		issue.setTitle("학습목표 2");
////		issue.setCourse(course);
//		lecture.getIssues().add(issue);
//		issueRepository.save(issue);
//		lectureRepository.save(lecture);
//
//		issue = new Issue();
//		issue.setTitle("학습목표 3");
////		issue.setCourse(course);
//		lecture.getIssues().add(issue);
//		issueRepository.save(issue);
//		lectureRepository.save(lecture);
//
//		course = new Course();
//		lecture.setTitle("수업 2");
////		course.setLecture(lecture);
//		course.getLectures().add(lecture);
//		lecture.setIssues(new ArrayList<>());
//		courseRepository.save(course);
//		lectureRepository.save(lecture);
//
//		course = new Course();
//		lecture.setTitle("수업 3");
////		course.setLecture(lecture);
//		course.getLectures().add(lecture);
//		lecture.setIssues(new ArrayList<>());
//		courseRepository.save(course);
//		lectureRepository.save(lecture);
//
//		return "ok!";
//	}
//}
