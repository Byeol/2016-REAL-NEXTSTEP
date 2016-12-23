package org.nhnnext.nextstep.lesson.domain;

import lombok.RequiredArgsConstructor;
import org.nhnnext.nextstep.core.web.AuditingController;
import org.nhnnext.nextstep.lesson.Lesson;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lessons/{id}")
public class LessonController extends AuditingController<Lesson> {

}
