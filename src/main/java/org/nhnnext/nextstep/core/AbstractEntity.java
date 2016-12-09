package org.nhnnext.nextstep.core;

import org.springframework.hateoas.Identifiable;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity extends AbstractPersistable<Long> implements Identifiable<Long> {
}
