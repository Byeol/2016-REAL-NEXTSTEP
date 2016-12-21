package org.nhnnext.nextstep.session;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue(SessionType.Values.MASTER)
public class MasterSession extends Session {
}
