package com.voc.boot.dict.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/19 21:26
 */
public abstract class ListTypeHandler<T> extends BaseTypeHandler<Set<T>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<T> parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setObject(i, fromList(parameter));
        } else {
            ps.setObject(i, fromList(parameter), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public Set<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object value = rs.getObject(columnName);
        return toList(value);
    }

    @Override
    public Set<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object value = rs.getObject(columnIndex);
        return toList(value);
    }

    @Override
    public Set<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object value = cs.getObject(columnIndex);
        return toList(value);
    }

    protected abstract Object fromList(Set<T> parameter);

    protected abstract Set<T> toList(Object value);
}
