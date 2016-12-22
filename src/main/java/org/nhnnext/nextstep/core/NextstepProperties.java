package org.nhnnext.nextstep.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix="nextstep")
public class NextstepProperties {

    private final List<String> admins = new ArrayList<>();
    private final List<String> instructors = new ArrayList<>();
}