package org.nhnnext.nextstep.enrollment.web;


import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.course.CourseRepository;
import org.nhnnext.nextstep.enrollment.Enrollment;
import org.nhnnext.nextstep.enrollment.EnrollmentRepository;
import org.nhnnext.nextstep.session.MySessionRepository;
import org.nhnnext.nextstep.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class EnrollmentResourceIntegrationTests extends AbstractIntegratedRepositoryTest<Session, MySessionRepository> {

    @Autowired
    protected MockMvc mvc;

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void test() throws Exception {
        Session session = createCourseSession();
        Session entity = (Session) withMockInstructor(() -> save(session));

        mvc.perform(put("/api/sessions/" + entity.getId() + "/enrollment"))
                .andDo(print());
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
//                .andExpect(jsonPath("$.name", equalTo(course.getName())))
//                .andExpect(jsonPath("$.instructors[0].username", equalTo(course.getInstructors().get(0).getUsername())));
    }

}
