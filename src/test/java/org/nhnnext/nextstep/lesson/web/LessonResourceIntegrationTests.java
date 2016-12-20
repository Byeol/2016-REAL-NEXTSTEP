package org.nhnnext.nextstep.lesson.web;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.lecture.Lecture;
import org.nhnnext.nextstep.lecture.LectureRepository;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.lesson.LessonRepository;
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
public class LessonResourceIntegrationTests extends AbstractIntegratedRepositoryTest<Lesson, LessonRepository> {

    @Autowired
    protected MockMvc mvc;

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void getExcerpt() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));

        mvc.perform(get("/api/lessons/" + entity.getId() + "?projection=excerpt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.name", equalTo(entity.getName())))
                .andExpect(jsonPath("$._links.self.href", notNullValue()));
    }

    @Test
    public void getLecture() throws Exception {
        Lesson lesson = createLesson();
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));

        mvc.perform(get("/api/lessons/" + entity.getId() + "/lecture"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.lessons[0].name", equalTo(entity.getName())))
                .andExpect(jsonPath("$.lessons[0]._links.self.href", notNullValue()));
    }
}
