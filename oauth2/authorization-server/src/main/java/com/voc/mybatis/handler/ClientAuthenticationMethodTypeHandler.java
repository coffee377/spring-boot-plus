package com.voc.mybatis.handler;

import com.voc.common.api.dict.FuncEnumDictItem;
import com.voc.common.api.func.Functions;
import com.voc.security.oauth2.enums.OAuth2ClientAuthenticationMethod;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

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
//@Component
@MappedTypes({Collection.class, Set.class, List.class})
@ConditionalOnClass({TypeHandler.class})
public class ClientAuthenticationMethodTypeHandler<T extends Collection<OAuth2ClientAuthenticationMethod>> extends BaseTypeHandler<T> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setObject(i, getValue(parameter));
        } else {
            ps.setObject(i, getValue(parameter), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object value = rs.getObject(columnName);
        return valueOf(value);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object value = rs.getObject(columnIndex);
        return valueOf(value);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object value = cs.getObject(columnIndex);
        return valueOf(value);
    }

    private Object getValue(T methods) {
        Functions functions = Functions.builder().functions(methods).build();
        return functions.getFunctions();
    }

    private T valueOf(Object value) {
        Functions functions = Functions.builder().of(String.valueOf(value)).build();
        List<OAuth2ClientAuthenticationMethod> functions1 = FuncEnumDictItem.getFunctions(OAuth2ClientAuthenticationMethod.class, functions);
        Stream<OAuth2ClientAuthenticationMethod> oAuth2ClientAuthenticationMethodStream = functions1.stream().map(new Function<OAuth2ClientAuthenticationMethod, OAuth2ClientAuthenticationMethod>() {
            @Override
            public OAuth2ClientAuthenticationMethod apply(OAuth2ClientAuthenticationMethod oAuth2ClientAuthenticationMethod) {
                return oAuth2ClientAuthenticationMethod;
            }
        });
        return null;
    }
}
