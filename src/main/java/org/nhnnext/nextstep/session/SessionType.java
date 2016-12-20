package org.nhnnext.nextstep.session;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SessionType {
    MASTER(Values.MASTER),
    COURSE(Values.COURSE);

    private final String value;

    public static class Values {
        public static final String MASTER = "M";
        public static final String COURSE = "C";
    }
}