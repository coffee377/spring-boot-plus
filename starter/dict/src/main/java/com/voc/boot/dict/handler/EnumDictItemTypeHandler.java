package com.voc.boot.dict.handler;

import com.voc.common.api.dict.EnumDictItem;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mybatis EnumDictItem 枚举类型处理器
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/17 14:31
 * @see EnumDictItem
 * @see TypeHandlerRegistry#register(Class)
 */
@MappedTypes(EnumDictItem.class)
public class EnumDictItemTypeHandler<T extends EnumDictItem<?>> extends BaseTypeHandler<T> {
    private final Class<T> clazz;

    public EnumDictItemTypeHandler(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            this.clazz = clazz;
        }
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setObject(i, this.getValue(parameter));
        } else {
            ps.setObject(i, this.getValue(parameter), jdbcType.TYPE_CODE);
        }
    }

    private Object getValue(T parameter) {
        return parameter.getValue();
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        Object object = resultSet.getObject(columnName);
        return valueOf(object);
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Object object = resultSet.getObject(i);
        return valueOf(object);
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Object object = callableStatement.getObject(i);
        return valueOf(object);
    }

    private T valueOf(Object value) {
        return EnumDictItem.findByValue(clazz, value).orElse(null);
    }

}
