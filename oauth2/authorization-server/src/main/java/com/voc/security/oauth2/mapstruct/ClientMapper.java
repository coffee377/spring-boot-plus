package com.voc.security.oauth2.mapstruct;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/22 16:50
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
//    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
//
//    OAuth2Client convertRegisteredClient2OAuth2Client(RegisteredClient registeredClient);
//
//    /**
//     * 持久化对象转视图对象
//     *
//     * @param userPO UserPO
//     * @return UserVO
//     */
//    RegisteredClient convertOAuth2Client2RegisteredClient(OAuth2Client userPO);
}
