package org.nhnnext.nextstep.lecture;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

public class LectureRepositoryCreateTests extends AbstractIntegratedRepositoryTest<Lecture, LectureRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Lecture lecture = createLecture();
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> save(lecture));
    }

    @Test
    public void withMockUser() throws Exception {
        Lecture lecture = createLecture();
        thrown.expect(AccessDeniedException.class);
        withMockUser(() -> save(lecture));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Lecture lecture = createLecture();
        Lecture entity = (Lecture) withMockInstructor(() -> save(lecture));
        Lecture result = (Lecture) withMockInstructor(() -> findOne(entity.getId()));
        Assert.assertEquals(entity, result);
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Lecture lecture = createLecture();
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> save(lecture));
    }
}
