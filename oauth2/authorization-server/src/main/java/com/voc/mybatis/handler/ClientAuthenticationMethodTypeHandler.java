package com.voc.mybatis.handler;

import com.voc.boot.dict.handler.FuncEnumDictItemTypeHandler;
import com.voc.common.api.dict.FuncEnumDictItem;
import com.voc.common.api.func.Functions;
import com.voc.security.oauth2.enums.OAuth2ClientAuthenticationMethod;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/08 22:43
 */
@Component
@MappedTypes({List.class, Set.class})
@ConditionalOnClass({TypeHandler.class})
public class ClientAuthenticationMethodTypeHandler extends FuncEnumDictItemTypeHandler<OAuth2ClientAuthenticationMethod> {

}
