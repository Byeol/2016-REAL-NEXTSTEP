//package org.nhnnext.web;
//
//import lombok.RequiredArgsConstructor;
//import org.nhnnext.domain.Course;
//import org.nhnnext.domain.Lecture;
//import org.nhnnext.domain.repository.CourseRepository;
//import org.nhnnext.domain.repository.LectureRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.RepositoryRestController;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@RepositoryRestController
//public class CourseController {
//
//	private final CourseRepository courseRepository;
//	private final LectureRepository lectureRepository;
//
//	@PostMapping("/courses/${id}/lectures")
//	public ResponseEntity<?> createLecture(@PathVariable("id") Course course, @RequestBody Lecture lecture) {
//
//		if (course == null) {
//			return ResponseEntity.notFound().build();
//		}
//
//		lecture.setCourse(course);
//		lectureRepository.save(lecture);
//
//		ResponseEntity.created()
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}
//}
