package org.nhnnext.nextstep.enrollment.web;

import lombok.RequiredArgsConstructor;
import org.nhnnext.nextstep.enrollment.Enrollment;
import org.nhnnext.nextstep.session.Session;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/sessions/{id}")
@ExposesResourceFor(Enrollment.class)
@RequiredArgsConstructor
public class EnrollmentController {

    private final EntityLinks entityLinks;

    @PutMapping(EnrollmentLinks.ENROLLMENT)
    ResponseEntity<?> submitEnrollment(@PathVariable("id") Session session) {
        System.out.println("TEST");
        System.out.println(session);

        return null;
    }
}
