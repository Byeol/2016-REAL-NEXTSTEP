package org.nhnnext.nextstep.enrollment.web;


import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.enrollment.Enrollment;
import org.nhnnext.nextstep.enrollment.EnrollmentRepository;
import org.springframework.hateoas.MediaTypes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EnrollmentResourceIntegrationTests extends AbstractIntegratedRepositoryTest<Enrollment, EnrollmentRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void getExcerpt() throws Exception {
        Enrollment enrollment = createEnrollment();
        Enrollment entity = (Enrollment) withMockUser(() -> save(enrollment));

        mvc.perform(get("/api/enrollments/" + entity.getId() + "?projection=excerpt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.status", equalTo(entity.getStatus().toString())))
                .andExpect(jsonPath("$.user.username", equalTo(entity.getUser().getUsername())));
    }
}
