package org.nhnnext.nextstep.session;

import lombok.Data;
import org.nhnnext.nextstep.core.AbstractEntity;
import org.nhnnext.nextstep.course.Course;

import javax.persistence.*;

//@NoArgsConstructor(force = true)
@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Session extends AbstractEntity {

    @ManyToOne //(fetch = FetchType.EAGER) //(cascade = CascadeType.PERSIST)//(optional = false)
//    @Cascade(CascadeType.ALL)
//    @JoinColumn(name="COURSE_ID")
    private Course course;
}
