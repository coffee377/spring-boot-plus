package com.voc.security.core.authentication.qrcode;

import com.voc.restful.core.third.ThirdAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/24 21:52
 */
@Slf4j
public class QRAuthenticationProvider implements AuthenticationProvider, InitializingBean {

    private final Map<String, QRAuthenticationUserDetailsService> authenticationUserDetailsServiceMap;
    private UserDetailsChecker userDetailsChecker = new AccountStatusUserDetailsChecker();
    private boolean throwExceptionWhenTokenRejected = false;

    public QRAuthenticationProvider(Collection<ThirdAppService> thirdAppServices) {
        this.authenticationUserDetailsServiceMap = thirdAppServices.stream().collect(Collectors.toMap(
                thirdAppService -> thirdAppService.getAppInfo().get(),
                thirdAppService -> {
                    QRAuthenticationUserDetailsService service = new QRAuthenticationUserDetailsService();
                    service.setThirdAppService(thirdAppService);
                    return service;
                }));
    }

    /**
     * Check whether all required properties have been set.
     */
    @Override
    public void afterPropertiesSet() {
        Assert.notNull(authenticationUserDetailsServiceMap, "An AuthenticationUserDetailsService must be set");
    }

    /**
     * Authenticate the given PreAuthenticatedAuthenticationToken.
     * <p>
     * If the principal contained in the authentication object is null, the request will
     * be ignored to allow other providers to authenticate it.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        if (authentication.getPrincipal() == null) {
            if (throwExceptionWhenTokenRejected) {
                throw new BadCredentialsException("No principal found in request.");
            }
            return null;
        }

        if (authentication.getCredentials() == null) {
            if (throwExceptionWhenTokenRejected) {
                throw new BadCredentialsException("No credentials found in request.");
            }
            return null;
        }

        QRAuthenticationToken token = (QRAuthenticationToken) authentication;

        checkThirdAppService(token.getType());

        AuthenticationUserDetailsService<QRAuthenticationToken> authenticationUserDetailsService =
                authenticationUserDetailsServiceMap.get(token.getType());

        UserDetails userDetails = authenticationUserDetailsService.loadUserDetails(token);

        userDetailsChecker.check(userDetails);

        QRAuthenticationToken result = new QRAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
        result.setDetails(authentication.getDetails());

        return result;
    }

    private void checkThirdAppService(String type) {
        if (!this.authenticationUserDetailsServiceMap.containsKey(type)) {
            throw new AuthenticationServiceException("No match to ThirdAppService for " + type);
        }
    }

    /**
     * Indicate that this provider only supports QRAuthenticationToken
     * (sub)classes.
     */
    @Override
    public final boolean supports(Class<?> authentication) {
        return QRAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * If true, causes the provider to throw a BadCredentialsException if the presented
     * authentication request is invalid (contains a null principal or credentials).
     * Otherwise it will just return null. Defaults to false.
     */
    public void setThrowExceptionWhenTokenRejected(boolean throwExceptionWhenTokenRejected) {
        this.throwExceptionWhenTokenRejected = throwExceptionWhenTokenRejected;
    }

    /**
     * Sets the strategy which will be used to validate the loaded <tt>UserDetails</tt>
     * object for the user. Defaults to an {@link AccountStatusUserDetailsChecker}.
     *
     * @param userDetailsChecker UserDetailsChecker
     */
    public void setUserDetailsChecker(UserDetailsChecker userDetailsChecker) {
        Assert.notNull(userDetailsChecker, "userDetailsChecker cannot be null");
        this.userDetailsChecker = userDetailsChecker;
    }

}
