package com.voc.common.dict.handler;

import com.voc.common.dict.enumeration.EnumDictItem;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * mybatis EnumDictItem 枚举类型处理器
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/17 14:31
 * @see EnumDictItem
 */
@Component
@MappedTypes(EnumDictItem.class)
public class EnumDictItemTypeHandler<Dict extends EnumDictItem> extends BaseTypeHandler<Dict> {
    private final Class<Dict> clazz;

    public EnumDictItemTypeHandler(Class<Dict> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            this.clazz = clazz;
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Dict parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setObject(i, this.getValue(parameter));
        } else {
            ps.setObject(i, this.getValue(parameter), jdbcType.TYPE_CODE);
        }
    }

    private Object getValue(Dict parameter) {
        return parameter.getValue();
    }

    @Override
    public Dict getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        Object object = resultSet.getObject(columnName);
        return valueOf(object);
    }

    @Override
    public Dict getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Object object = resultSet.getObject(i);
        return valueOf(object);
    }

    @Override
    public Dict getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Object object = callableStatement.getObject(i);
        return valueOf(object);
    }

    private Dict valueOf(Object value) {
        Optional<Dict> dict = EnumDictItem.findByValue(clazz, value);
        return dict.orElse(null);
    }

}
