package org.nhnnext.nextstep.lecture.web;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.lecture.Lecture;
import org.nhnnext.nextstep.lecture.LectureRepository;
import org.nhnnext.nextstep.session.CourseSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class LectureResourceIntegrationTests extends AbstractIntegratedRepositoryTest<Lecture, LectureRepository> {

    @Autowired
    protected MockMvc mvc;

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void getExcerpt() throws Exception {
        Lecture lecture = createLecture();
        Lecture entity = (Lecture) withMockInstructor(() -> save(lecture));

        mvc.perform(get("/api/lectures/" + entity.getId() + "?projection=excerpt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.name", equalTo(entity.getName())))
                .andExpect(jsonPath("$._links.self.href", notNullValue()));
    }
}
