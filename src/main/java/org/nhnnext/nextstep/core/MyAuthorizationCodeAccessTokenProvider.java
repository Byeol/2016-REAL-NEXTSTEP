package org.nhnnext.nextstep.core;

import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;

public class MyAuthorizationCodeAccessTokenProvider extends AuthorizationCodeAccessTokenProvider {

    public MyAuthorizationCodeAccessTokenProvider() {
        setStateMandatory(false);
    }
}
