package org.nhnnext.nextstep.course.web;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.nhnnext.nextstep.course.Course;
import org.nhnnext.nextstep.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class CourseResourceIntegrationTests extends AbstractIntegratedRepositoryTest<Course, CourseRepository> {

    @Autowired
    protected MockMvc mvc;

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void getExcerpt() throws Exception {
        Course course = createCourse();
        Course entity = (Course) withMockInstructor(() -> save(course));

        mvc.perform(get("/api/courses/" + entity.getId() + "?projection=excerpt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.name", equalTo(course.getName())))
                .andExpect(jsonPath("$.instructors[0].username", equalTo(course.getInstructors().get(0).getUsername())));
    }

    @Test
    public void getDetail() throws Exception {
        Course course = createCourse();
        Course entity = (Course) withMockInstructor(() -> save(course));

        mvc.perform(get("/api/courses/" + entity.getId() + "?projection=detail"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.name", equalTo(course.getName())))
                .andExpect(jsonPath("$.description", equalTo(course.getDescription())))
                .andExpect(jsonPath("$.instructors[0].username", equalTo(course.getInstructors().get(0).getUsername())));
    }
}
