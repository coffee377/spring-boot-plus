package com.voc.mybatis.handler;

import com.voc.boot.dict.handler.FuncEnumDictItemTypeHandler;
import com.voc.security.oauth2.enums.OAuth2AuthorizationGrantType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/20 00:41
 */
@Component
@MappedTypes({List.class, Set.class})
@ConditionalOnClass({TypeHandler.class})
public class OAuth2AuthorizationGrantTypeTypeHandler extends FuncEnumDictItemTypeHandler<OAuth2AuthorizationGrantType> {
}
