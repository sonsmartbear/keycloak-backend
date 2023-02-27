package com.eviware.keycloak.backend.infra.security.config;

import com.eviware.keycloak.backend.infra.security.AccessToken;

public interface CustomAuthenticationToken {

    public AccessToken getAccessToken() ;
}
