package org.nhnnext.nextstep.session.web;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.session.CourseSession;
import org.nhnnext.nextstep.session.MySessionRepository;
import org.nhnnext.nextstep.session.Session;
import org.springframework.hateoas.MediaTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SessionResourceIntegrationTests extends AbstractIntegratedRepositoryTest<Session, MySessionRepository> {

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
                .andExpect(jsonPath("$.description", equalTo(session.getDescription())))
                .andExpect(jsonPath("$.state", equalTo(session.getState().toString())));
//                .andExpect(jsonPath("$.lectures", emptyArray()))
//                .andExpect(jsonPath("$.lecturePos", emptyArray()));
    }

    @Test
    public void updatePosWithoutSubLecture() throws Exception {
        updatePos(new ArrayList<>(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void updatePosWithSubLecture() throws Exception {
        updatePos(new ArrayList<>(Arrays.asList(1, 2, Arrays.asList(3, 4), 5)));
    }

    @Test
    public void updatePosStartWithSubLecture() throws Exception {
        updatePos(new ArrayList<>(Arrays.asList(Arrays.asList(3, 4))));
    }

    @Test
    public void updatePosTwice() throws Exception {
        updatePos(new ArrayList<>(Arrays.asList(Arrays.asList(1, 2))));
        updatePos(new ArrayList<>(Arrays.asList(Arrays.asList(1, 2))));
    }

    public void updatePos(Object pos) throws Exception {
        CourseSession session = createCourseSession();
        CourseSession entity = (CourseSession) withMockInstructor(() -> save(session));

        Map<String, Object> content = new HashMap<>();
        content.put("pos", pos);

        withMockInstructor(() -> mvc.perform(patch("/api/sessions/" + entity.getId()).content(json(content)))
                .andDo(print())
                .andExpect(status().isNoContent()));

        withMockInstructor(() -> mvc.perform(get("/api/sessions/" + entity.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.pos", equalTo(content.get("pos")))));
    }
}
