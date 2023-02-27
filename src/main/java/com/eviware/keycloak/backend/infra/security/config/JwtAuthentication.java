package com.eviware.keycloak.backend.infra.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.eviware.keycloak.backend.infra.security.AccessToken;
import com.eviware.keycloak.backend.infra.security.InvalidTokenException;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.List;

import static java.util.Objects.isNull;

@ToString
@Slf4j
public class JwtAuthentication extends AbstractAuthenticationToken implements CustomAuthenticationToken {

    private final AccessToken accessToken;

    public JwtAuthentication(AccessToken accessToken) {
        super(accessToken.getAuthorities());
        this.accessToken = accessToken;

    }

    public List<String> getAudiences() {
        return accessToken.getAudiences();
    }


    @Override
    public Object getCredentials() {
        return accessToken.getValue();
    }

    @Override
    public Object getPrincipal() {
        return accessToken.getUsername();
    }

    @Override
    public AccessToken getAccessToken() {
        return accessToken;
    }
}
