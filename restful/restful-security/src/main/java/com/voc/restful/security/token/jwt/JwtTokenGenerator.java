//package com.voc.security.token.jwt;
//
//import com.voc.security.token.TokenGenerator;
//import com.voc.security.token.TokenProperties;
//import com.voc.security.token.TokenResult;
//import com.voc.security.token.TokenStorage;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
//import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
//import org.springframework.security.oauth2.jwt.JoseHeader;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//
//import java.lang.reflect.Field;
//import java.time.Duration;
//import java.time.Instant;
//import java.util.Collections;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2021/06/16 09:34
// */
//public class JwtTokenGenerator implements TokenGenerator, InitializingBean {
//
//    private final TokenProperties tokenProperties;
//    private final JwtProperties jwtProperties;
//    private final TokenStorage tokenStorage;
//    private final JwtEncoder jwtEncoder;
//
//    public JwtTokenGenerator(TokenProperties tokenProperties, TokenStorage tokenStorage,
//                             JwtProperties jwtProperties, JwtEncoder jwtEncoder) {
//        this.tokenProperties = tokenProperties;
//        this.jwtProperties = jwtProperties;
//        this.tokenStorage = tokenStorage;
//        this.jwtEncoder = jwtEncoder;
//    }
//
//    @Override
//    public OAuth2AccessTokenResponse tokenResponse(UserDetails userDetails) {
//        return this.tokenResponse(Instant.now(), userDetails);
//    }
//
//    @Override
//    public OAuth2AccessTokenResponse tokenResponse(Instant issuedAt, UserDetails userDetails) {
//        SignatureAlgorithm signatureAlgorithm = jwtProperties.getSignatureAlgorithm();
//        JoseHeader joseHeader = JoseHeader.withAlgorithm(signatureAlgorithm).type("JWT").build();
//
//        Set<String> scopes = userDetails.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toSet());
//
//        String issuer = jwtProperties.getIssuer();
//        /* token 所有者 */
//        String subject = userDetails.getUsername();
//        String username = userDetails.getUsername();
//
//        JwtClaimsSet sharedClaims = JwtClaimsSet.builder()
//                .issuer(issuer)
//                .subject(subject)
////                .audience(Collections.singletonList(username))
//                .claim("scope", scopes)
//                .claim("iat-milliseconds", issuedAt.toEpochMilli())
//                .issuedAt(issuedAt)
//                .build();
//
//        Duration accessTokenExpiresIn = tokenProperties.getAccessTokenExpiresIn();
//        Instant accessTokenExpiresAt = issuedAt.plus(accessTokenExpiresIn);
//        Jwt accessToken = jwtEncoder.encode(joseHeader, JwtClaimsSet.from(sharedClaims)
//                .expiresAt(accessTokenExpiresAt)
//                .build());
//
//        Duration refreshTokenExpiresIn = tokenProperties.getRefreshTokenExpiresIn();
//        Instant refreshTokenExpiresAt = issuedAt.plus(refreshTokenExpiresIn);
//        Jwt refreshToken = jwtEncoder.encode(joseHeader,
//                JwtClaimsSet.from(sharedClaims).claim("scope", Collections.singletonList("refresh_token"))
//                        .expiresAt(refreshTokenExpiresAt)
//                        .build());
//
//
//        OAuth2AccessTokenResponse.Builder builder = OAuth2AccessTokenResponse.withToken(accessToken.getTokenValue())
//                .tokenType(OAuth2AccessToken.TokenType.BEARER)
//                .expiresIn(accessTokenExpiresIn.getSeconds())
//                .scopes(scopes)
//                .refreshToken(refreshToken.getTokenValue());
//
//        syncIssuedAt(builder, issuedAt);
//
//        return builder.build();
//    }
//
//    /**
//     * 同步 TOKEN 签发时间
//     *
//     * @param builder  OAuth2AccessTokenResponse.Builder
//     * @param issuedAt Instant
//     */
//    private void syncIssuedAt(OAuth2AccessTokenResponse.Builder builder, Instant issuedAt) {
//        /* 由于 OAuth2AccessTokenResponse 类是 final 修饰的，且 内部类 Builder 未提供 issuedAt 的赋值方法，这里通过反射赋值*/
//        try {
//            Class clazz = builder.getClass();
//            Field field = clazz.getDeclaredField("issuedAt");
//            field.setAccessible(true);
//            field.set(builder, issuedAt);
//        } catch (Exception ignored) {
//        }
//    }
//
//    @Override
//    public TokenResult create(Instant issuedAt, UserDetails userDetails, boolean onlyAccessToken) {
//        if (userDetails == null) {
//            return null;
//        }
//        OAuth2AccessTokenResponse accessTokenResponse = tokenResponse(issuedAt, userDetails);
//        String username = userDetails.getUsername();
//        String accessToken = tokenStorage.createAccessToken(username, accessTokenResponse);
//        TokenResult tokenResult = new TokenResult(accessToken);
//        if (!onlyAccessToken) {
//            String refreshToken = tokenStorage.createRefreshToken(username, accessTokenResponse);
//            tokenResult.setRefreshToken(refreshToken);
//        }
//        return tokenResult;
//    }
//
//    @Override
//    public String refresh(UserDetailsService userDetailsService, Authentication authentication) {
//        String username = authentication.getName();
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        OAuth2AccessTokenResponse accessTokenResponse = tokenResponse(Instant.now(), userDetails);
//        return tokenStorage.createAccessToken(username, accessTokenResponse);
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//
//    }
//}
