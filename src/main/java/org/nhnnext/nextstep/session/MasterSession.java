package org.nhnnext.nextstep.session;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor(force = true)
@Data
@Entity
@DiscriminatorValue("MASTER")
public class MasterSession extends Session {

}
