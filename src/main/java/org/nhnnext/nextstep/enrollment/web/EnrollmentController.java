package org.nhnnext.nextstep.enrollment.web;

import lombok.RequiredArgsConstructor;
import org.nhnnext.nextstep.enrollment.Enrollment;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sessions/{id}")
@ExposesResourceFor(Enrollment.class)
@RequiredArgsConstructor
public class EnrollmentController {
}
