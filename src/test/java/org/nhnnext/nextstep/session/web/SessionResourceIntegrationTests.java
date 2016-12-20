package org.nhnnext.nextstep.session.web;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.session.MySessionRepository;
import org.nhnnext.nextstep.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class SessionResourceIntegrationTests extends AbstractIntegratedRepositoryTest<Session, MySessionRepository> {

    @Autowired
    protected MockMvc mvc;

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void getDetail() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));

        mvc.perform(get("/api/sessions/" + entity.getId() + "?projection=detail"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.name", equalTo(session.getName())))
                .andExpect(jsonPath("$.description", equalTo(session.getDescription())));
    }

    @Test
    public void updatePos() throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));

        Map<String, Object> content = new HashMap<>();
        content.put("lecturePos", new ArrayList<>(Arrays.asList(1, 2, Arrays.asList(3, 4), 5)));

        withMockInstructor(() -> mvc.perform(patch("/api/sessions/" + entity.getId()).content(json(content)))
                .andDo(print())
                .andExpect(status().isNoContent()));

        withMockInstructor(() -> mvc.perform(get("/api/sessions/" + entity.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.lecturePos", equalTo(content.get("lecturePos")))));
//                .andExpect(jsonPath("$.lecturePos").value(content.get("lecturePos"))));//equalTo(json(content)))));
    }
}
