package com.voc.security.oauth2.servcer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/01 00:15
 */
@Data
@Slf4j
public class OAuth2UsernamePasswordAuthenticationProvider implements AuthenticationProvider, InitializingBean {
    private boolean forcePrincipalAsString = false;

    protected boolean hideUserNotFoundExceptions = true;
    private UserCache userCache = new NullUserCache();
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    private UserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();
    private UserDetailsChecker postAuthenticationChecks = new DefaultPostAuthenticationChecks();

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    private OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;

    public OAuth2UsernamePasswordAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OAuth2UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (OAuth2UsernamePasswordAuthenticationToken) authentication;
        OAuth2ClientAuthenticationToken clientPrincipal = getAuthenticatedClientElseThrowInvalidClient(authentication);
        RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();

        String username = usernamePasswordAuthenticationToken.getUsername();
        boolean cacheWasUsed = true;
        UserDetails user = this.userCache.getUserFromCache(username);
        if (user == null) {
            cacheWasUsed = false;
            try {
                user = retrieveUser(username, usernamePasswordAuthenticationToken);
            } catch (UsernameNotFoundException ex) {
                log.debug("Failed to find user {}'", username);
                if (!this.hideUserNotFoundExceptions) {
                    throw ex;
                }
                throw new BadCredentialsException("Bad credentials");
            }
            Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");
        }
        try {
            this.preAuthenticationChecks.check(user);
            additionalAuthenticationChecks(user, usernamePasswordAuthenticationToken);
        } catch (AuthenticationException ex) {
            if (!cacheWasUsed) {
                throw ex;
            }
            // There was a problem, so try again after checking
            // we're using the latest data (i.e. not from the cache)
            cacheWasUsed = false;
            user = retrieveUser(username, usernamePasswordAuthenticationToken);
            this.preAuthenticationChecks.check(user);
            additionalAuthenticationChecks(user, usernamePasswordAuthenticationToken);
        }
        this.postAuthenticationChecks.check(user);
        if (!cacheWasUsed) {
            this.userCache.putUserInCache(user);
        }
        usernamePasswordAuthenticationToken.setAuthenticated(true);
        return createSuccessAuthentication(registeredClient, clientPrincipal, usernamePasswordAuthenticationToken, user);
    }

    private OAuth2AccessTokenAuthenticationToken createSuccessAuthentication(RegisteredClient registeredClient,
                                                                             Authentication clientPrincipal,
                                                                             Authentication userPrincipal,
                                                                             UserDetails userDetails) {

        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient).authorizationGrantType(AuthorizationGrantType.PASSWORD);
        DefaultOAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
        OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
        OAuth2AccessToken accessToken = null;
        OAuth2RefreshToken refreshToken = null;

        OAuth2AccessTokenAuthenticationToken authenticationResult =
                new OAuth2AccessTokenAuthenticationToken(registeredClient, clientPrincipal, accessToken, refreshToken);
        authenticationResult.setDetails(userPrincipal.getDetails());
        authenticationResult.setAuthenticated(true);
        return authenticationResult;
    }

    private void additionalAuthenticationChecks(UserDetails userDetails, OAuth2UsernamePasswordAuthenticationToken authenticationToken) {
        if (authenticationToken.getCredentials() == null) {
            log.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException("Bad credentials");
        }
        String presentedPassword = authenticationToken.getCredentials().toString();
        if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            log.debug("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private UserDetails retrieveUser(String username, OAuth2UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return userDetailsService.loadUserByUsername(username);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OAuth2UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userDetailsService, "A UserDetailsService must be set");
        Assert.notNull(passwordEncoder, "A PasswordEncoder must be set");
    }

    private OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(Authentication authentication) {
        OAuth2ClientAuthenticationToken clientPrincipal = null;
        if (OAuth2ClientAuthenticationToken.class.isAssignableFrom(authentication.getPrincipal().getClass())) {
            clientPrincipal = (OAuth2ClientAuthenticationToken) authentication.getPrincipal();
        }

        if (clientPrincipal != null && clientPrincipal.isAuthenticated()) {
            return clientPrincipal;
        } else {
            throw new OAuth2AuthenticationException("invalid_client");
        }
    }

    private static class DefaultPreAuthenticationChecks implements UserDetailsChecker {
        @Override
        public void check(UserDetails user) {
            if (!user.isAccountNonLocked()) {
                log.debug("Failed to authenticate since user account is locked");
                throw new LockedException("User account is locked");
            }
            if (!user.isEnabled()) {
                log.debug("Failed to authenticate since user account is disabled");
                throw new DisabledException("User is disabled");
            }
            if (!user.isAccountNonExpired()) {
                log.debug("Failed to authenticate since user account has expired");
                throw new AccountExpiredException("User account has expired");
            }
        }

    }

    private static class DefaultPostAuthenticationChecks implements UserDetailsChecker {

        @Override
        public void check(UserDetails user) {
            if (!user.isCredentialsNonExpired()) {
                log.debug("Failed to authenticate since user account credentials have expired");
                throw new CredentialsExpiredException("User credentials have expired");
            }
        }

    }

}
