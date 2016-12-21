package org.nhnnext.nextstep.lecture;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.nextstep.core.AbstractIntegratedRepositoryTest;
import org.springframework.security.access.AccessDeniedException;

public class LectureRepositoryDeleteTests extends AbstractIntegratedRepositoryTest<Lecture, LectureRepository> {

    @Before
    public void init() {
        initRepository();
    }

    @Test
    public void withAnonymousUser() throws Exception {
        Lecture lecture = createLecture();
        Lecture entity = (Lecture) withMockInstructor(() -> save(lecture));
        thrown.expect(AccessDeniedException.class);
        withAnonymousUser(() -> delete(entity));
    }

    @Test
    public void withMockUser() throws Exception {
        Lecture lecture = createLecture();
        Lecture entity = (Lecture) withMockInstructor(() -> save(lecture));
        thrown.expect(AccessDeniedException.class);
        withMockUser(() -> delete(entity));
    }

    @Test
    public void withMockInstructor() throws Exception {
        Lecture lecture = createLecture();
        Lecture entity = (Lecture) withMockInstructor(() -> save(lecture));
        withMockInstructor(() -> delete(entity));
    }

    @Test
    public void withMockAlternativeInstructor() throws Exception {
        Lecture lecture = createLecture();
        Lecture entity = (Lecture) withMockInstructor(() -> save(lecture));
        thrown.expect(AccessDeniedException.class);
        withMockAlternativeInstructor(() -> delete(entity));
    }
}
