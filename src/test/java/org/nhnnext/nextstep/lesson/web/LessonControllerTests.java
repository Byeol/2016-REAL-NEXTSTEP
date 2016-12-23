package org.nhnnext.nextstep.lesson.web;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.lesson.Lesson;
import org.nhnnext.nextstep.lesson.LessonRepository;
import org.nhnnext.nextstep.user.GrantedAuthorities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyArray;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LessonControllerTests extends AbstractIntegratedRepositoryTest<Lesson, LessonRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withMockUser() throws Exception {
        Lesson lesson = createLesson();
        setLessonAsPublic(lesson);
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));

        withMockUser(() -> mvc.perform(get("/api/lessons/" + entity.getId() + "/authorities"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorities[*].grantedAuthority", containsInAnyOrder(GrantedAuthorities.COURSE_PARTICIPANT)))
        );
    }

    @Test
    public void withMockAlternativeUser() throws Exception {
        Lesson lesson = createLesson();
        setLessonAsPublic(lesson);
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));

        withMockAlternativeUser(() -> mvc.perform(get("/api/lessons/" + entity.getId() + "/authorities"))
                .andDo(print())
                .andExpect(status().isOk())
        );
    }

    @Test
    public void withMockInstructor() throws Exception {
        Lesson lesson = createLesson();
        setLessonAsPublic(lesson);
        Lesson entity = (Lesson) withMockInstructor(() -> save(lesson));

        withMockInstructor(() -> mvc.perform(get("/api/lessons/" + entity.getId() + "/authorities"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorities[*].grantedAuthority", containsInAnyOrder(GrantedAuthorities.ENTITY_OWNER, GrantedAuthorities.COURSE_INSTRUCTOR)))
        );
    }
}
