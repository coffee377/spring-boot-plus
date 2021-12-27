//package com.voc.security.token.jwt;
//
//import com.voc.security.token.RedisCacheTokenStorage;
//import org.springframework.data.redis.core.RedisOperations;
//import org.springframework.security.oauth2.core.OAuth2Error;
//import org.springframework.security.oauth2.core.OAuth2TokenValidator;
//import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.util.Assert;
//
//import java.util.List;
//import java.util.Set;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2021/06/18 21:45
// */
//public class JwtRedisCacheTokenValidator implements OAuth2TokenValidator<Jwt> {
//
//    private final RedisOperations<String, Object> redisOperations;
//
//    public JwtRedisCacheTokenValidator(RedisOperations<String, Object> redisOperations) {
//        this.redisOperations = redisOperations;
//    }
//
//    @Override
//    public OAuth2TokenValidatorResult validate(Jwt jwt) {
//        Assert.notNull(jwt, "jwt cannot be null");
//        if (!cacheExist(jwt)) {
//            OAuth2Error oAuth2Error = new OAuth2Error("0000","JWT Cache Invalid","");
//            return OAuth2TokenValidatorResult.failure(oAuth2Error);
//        }
//        return OAuth2TokenValidatorResult.success();
//    }
//
//    private boolean cacheExist(Jwt jwt) {
//        boolean refreshToken = false;
//        Object scope = jwt.getClaims().get("scope");
//        if (scope instanceof List) {
//            List list = (List) scope;
//            refreshToken = list.size() == 1 && "refresh_token".equals(list.get(0));
//        }
//
//        StringBuilder sb = new StringBuilder();
//        if (refreshToken) {
//            sb.append(RedisCacheTokenStorage.REFRESH_TOKEN_CACHE);
//        } else {
//            sb.append(RedisCacheTokenStorage.ACCESS_TOKEN_CACHE);
//        }
//        sb.append(":").append(jwt.getSubject());
//        sb.append(":").append(jwt.getClaims().get("iat-milliseconds"));
//
//        String cacheKey = sb.toString();
//
//        Set<String> keys = redisOperations.keys(cacheKey);
//
//        return keys != null && keys.size() > 0;
//    }
//
//}
