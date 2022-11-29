package com.voc.mybatis.handler;

import org.apache.ibatis.type.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/20 01:14
 */
@Component
@MappedTypes({Collection.class, List.class, Set.class})
@ConditionalOnClass({TypeHandler.class})
public class CollectionStringTypeHandler extends BaseTypeHandler<Collection<String>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Collection<String> parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setObject(i, from(parameter));
        } else {
            ps.setObject(i, from(parameter), jdbcType.TYPE_CODE);
        }
    }

    private String from(Collection<String> parameter) {
        return parameter.stream().filter(StringUtils::hasText).collect(Collectors.joining(","));
    }

    @Override
    public Collection<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        return to(string);
    }


    @Override
    public Collection<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        return to(string);
    }

    @Override
    public Collection<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        return to(string);
    }

    private static Set<String> to(String string) {
        List<String> strings = Arrays.asList(string.split(","));
        return new HashSet<>(strings);
    }
}
