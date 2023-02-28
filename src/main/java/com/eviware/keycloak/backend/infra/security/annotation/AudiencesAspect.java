package com.eviware.keycloak.backend.infra.security.annotation;

import com.eviware.keycloak.backend.infra.security.config.CustomAuthenticationToken;
import com.eviware.keycloak.backend.infra.security.config.JwtAuthentication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Order(2)
@Aspect
@Component
public class AudiencesAspect {

    @Before("@annotation(com.eviware.keycloak.backend.infra.security.annotation.AllowedAudiences)")
    public void before(JoinPoint joinPoint) {

        String[] expectedAud = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(AllowedAudiences.class).value();
        CustomAuthenticationToken customAuthenticationToken = ((CustomAuthenticationToken) SecurityContextHolder.getContext().getAuthentication());
        List<String> audiences = customAuthenticationToken.getAccessToken().getAudiences();
        System.out.println(audiences);
        if (!audiences.containsAll(Arrays.asList(expectedAud))) {
            throw new AccessDeniedException(String.format("Unauthorized request. Expected to have %s audiences, but have %s", Arrays.asList(expectedAud), audiences));
        }
    }
}
