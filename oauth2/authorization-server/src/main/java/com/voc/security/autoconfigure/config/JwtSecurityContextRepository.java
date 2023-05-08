package com.voc.security.autoconfigure.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.util.function.SingletonSupplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;


/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.3
 */
public class JwtSecurityContextRepository implements SecurityContextRepository {
    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        return null;
    }

    @Override
    public Supplier<SecurityContext> loadContext(HttpServletRequest request) {
//        Jwt jwt = Jwt.withTokenValue("").build();
//        JwtAuthenticationToken jwtAuthentication = new JwtAuthenticationToken(jwt);
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.authenticated("root",
                "123456", Collections.singletonList(new SimpleGrantedAuthority("admin")));

        SecurityContext securityContext = new SecurityContextImpl(authenticationToken);
        return SingletonSupplier.of(() -> securityContext);
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        String header = request.getHeader("X-Token");
        return false;
    }
}
