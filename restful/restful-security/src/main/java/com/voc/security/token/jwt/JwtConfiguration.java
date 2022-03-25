//package com.voc.security.token.jwt;
//
//import com.nimbusds.jose.JOSEException;
//import com.nimbusds.jose.JWSAlgorithm;
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
//import com.nimbusds.jose.jwk.source.JWKSource;
//import com.nimbusds.jose.proc.JWSKeySelector;
//import com.nimbusds.jose.proc.JWSVerificationKeySelector;
//import com.nimbusds.jose.proc.SecurityContext;
//import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
//import com.nimbusds.jwt.proc.DefaultJWTProcessor;
//import com.voc.security.token.TokenGenerator;
//import com.voc.security.token.TokenProperties;
//import com.voc.security.token.TokenStorage;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.data.redis.core.RedisOperations;
//import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
//import org.springframework.security.oauth2.core.OAuth2TokenValidator;
//import org.springframework.security.oauth2.jwt.*;
//
//import java.security.KeyStore;
//import java.security.KeyStoreException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2021/06/16 10:33
// */
//@EnableConfigurationProperties(JwtProperties.class)
//public class JwtConfiguration {
//
//    @Bean
//    @ConditionalOnMissingBean
//    TokenGenerator jwtTokenGenerator(TokenProperties tokenProperties, TokenStorage tokenStorage,
//                                     JwtProperties jwtProperties, JwtEncoder jwtEncoder) {
//        return new JwtTokenGenerator(tokenProperties, tokenStorage, jwtProperties, jwtEncoder);
//    }
//
//    @Bean
//    KeyStore jksStore() throws KeyStoreException {
//        return KeyStore.getInstance("jks");
//    }
//
//    @Bean
//    @SneakyThrows
//    @ConditionalOnMissingBean
//    RSAKey rsaKey(KeyStore keyStore) {
//        ClassPathResource classPathResource = new ClassPathResource("felordcn.jks");
//        char[] pin = "123456".toCharArray();
//        keyStore.load(classPathResource.getInputStream(), pin);
//        return RSAKey.load(keyStore, "felordcn", pin);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
//        JWKSet jwkSet = new JWKSet(rsaKey);
//        return new ImmutableJWKSet<>(jwkSet);
//    }
//
////    @Bean
////    @ConditionalOnMissingBean
////    public JWKSource<SecurityContext> jwkSource() {
////        RSAKey rsaKey = Jwks.generateRsa();
////        ECKey ecKey = Jwks.generateEc();
////        OctetSequenceKey octetSequenceKey = Jwks.generateSecret();
////        JWKSet jwkSet = new JWKSet(Arrays.asList(rsaKey, ecKey, octetSequenceKey));
////        return new ImmutableJWKSet<>(jwkSet);
////    }
//
//    /**
//     * 用JWK来生成JWT的工具，底层使用了Nimbus库，这个库是Spring Security OAuth2 Client 默认引用的库
//     *
//     * @param jwkSource the jwk source
//     * @return the jwt encoder
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
//        return new NimbusJwsEncoder(jwkSource);
//    }
//
//    /**
//     * 校验JWT issuer
//     *
//     * @return the jwt issuer validator
//     * @see DelegatingOAuth2TokenValidator
//     */
//    @Bean
//    @Order(1)
//    JwtIssuerValidator jwtIssuerValidator(JwtProperties jwtProperties) {
//        return new JwtIssuerValidator(jwtProperties.getIssuer());
//    }
//
//    /**
//     * 校验JWT是否过期
//     *
//     * @return the jwt timestamp validator
//     * @see DelegatingOAuth2TokenValidator
//     */
//    @Bean
//    @Order(2)
//    JwtTimestampValidator jwtTimestampValidator(TokenProperties tokenProperties) {
//        return new JwtTimestampValidator(tokenProperties.getAccessTokenExpiresIn());
//    }
//
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    @ConditionalOnProperty(prefix = "api.security.token", name = "invalid-when-cache-not-exist", havingValue = "true")
//    JwtRedisCacheTokenValidator jwtRedisCacheTokenValidator(@Qualifier("redisTemplate") RedisOperations<String, Object> redisOperations) {
//        return new JwtRedisCacheTokenValidator(redisOperations);
//    }
//
//    /**
//     * JWT 委托校验器，用来执行多个JWT校验策略，如果有其它校验需要可自行实现{@link OAuth2TokenValidator}
//     *
//     * @param tokenValidators the token validators
//     * @return the delegating o auth 2 token validator
//     */
//    @Primary
//    @Bean("delegatingTokenValidator")
//    public DelegatingOAuth2TokenValidator<Jwt> delegatingTokenValidator(List<OAuth2TokenValidator<Jwt>> tokenValidators) {
////        tokenValidators.sort(AnnotationAwareOrderComparator.INSTANCE);
//        return new DelegatingOAuth2TokenValidator<>(tokenValidators);
//    }
//
//    /**
//     * Jwt decoder jwt decoder.
//     *
//     * @param jwkSource the jwk source
//     * @return the jwt decoder
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource,
//                                 DelegatingOAuth2TokenValidator<Jwt> oauth2TokenValidator) throws JOSEException {
//        Set<JWSAlgorithm> jwsAlgorithms = new HashSet<>();
//        jwsAlgorithms.addAll(JWSAlgorithm.Family.RSA);
//        jwsAlgorithms.addAll(JWSAlgorithm.Family.EC);
//        jwsAlgorithms.addAll(JWSAlgorithm.Family.HMAC_SHA);
//        JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(jwsAlgorithms, jwkSource);
////        JWSVerificationKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, jwkSource);
//
//        ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
//        jwtProcessor.setJWSKeySelector(keySelector);
//        // Override the default Nimbus claims set verifier as NimbusJwtDecoder handles it instead
//        jwtProcessor.setJWTClaimsSetVerifier((claims, context) -> {
//        });
//        NimbusJwtDecoder nimbusJwtDecoder = new NimbusJwtDecoder(jwtProcessor);
//
//        nimbusJwtDecoder.setJwtValidator(oauth2TokenValidator);
//        return nimbusJwtDecoder;
//    }
//}
