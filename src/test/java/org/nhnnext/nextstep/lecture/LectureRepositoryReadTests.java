package org.nhnnext.nextstep.lecture;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;

import static org.junit.Assert.assertEquals;

public class LectureRepositoryReadTests extends AbstractIntegratedRepositoryTest<Lecture, LectureRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Lecture lecture = createLecture();
        Lecture entity = (Lecture) withMockInstructor(() -> save(lecture));
        Lecture result = (Lecture) withAnonymousUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockUser() throws Exception {
        Lecture lecture = createLecture();
        Lecture entity = (Lecture) withMockInstructor(() -> save(lecture));
        Lecture result = (Lecture) withMockUser(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockInstructor() throws Exception {
        Lecture lecture = createLecture();
        Lecture entity = (Lecture) withMockInstructor(() -> save(lecture));
        Lecture result = (Lecture) withMockInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Lecture lecture = createLecture();
        Lecture entity = (Lecture) withMockInstructor(() -> save(lecture));
        Lecture result = (Lecture) withMockAlternativeInstructor(() -> findOne(entity.getId()));
        assertEquals(entity, result);
    }
}
