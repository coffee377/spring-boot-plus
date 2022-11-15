package com.voc.boot.dict.handler;

import com.voc.common.api.dict.FuncEnumDictItem;
import com.voc.common.api.func.Functions;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.2
 */
//@MappedTypes({Collection.class, Set.class, List.class})
public class FuncEnumDictItemTypeHandler<F extends FuncEnumDictItem<?>, T extends Collection<F>> extends BaseTypeHandler<T> {
//    private final Class<F> funcClazz;
//    private final Class<T> clazz;

    public FuncEnumDictItemTypeHandler(Class<T> clazz) {
        Type rawType = getRawType();
        ResolvableType resolvableType = ResolvableType.forType(rawType);
        ResolvableType generic = resolvableType.getGeneric(0);
//        Class<?> clazz = generic.resolve();
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
//            this.clazz = clazz;
        }
    }


    public FuncEnumDictItemTypeHandler() {
//        ResolvableType.
    }

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


    private Object getValue(T parameter) {
//        Iterator<F> iterator = parameter.iterator();
//        F next = iterator.next();
//        Class<?> resolve = ResolvableType.forInstance(next.getValue()).resolve();
        Functions functions = Functions.builder().functions(parameter).build();
        // 根据 FuncEnumDictItem<?> 泛型类型返回指定类型数据
//        BigInteger result = functions.get();
        return functions.getFunctions();

    }

    //
    private T valueOf(Object value) {
        Type rawType = getRawType();
        ResolvableType resolvableType = ResolvableType.forType(rawType);
        ResolvableType generic = resolvableType.getGeneric(0);
        Functions functions = Functions.builder().of(String.valueOf(value)).build();
//        List<FuncEnumDictItem<?>> result = new ArrayList<>();
//        List<FuncEnumDictItem> result = FuncEnumDictItem.getFunctions(null, functions);
//        Class<? extends FuncEnumDictItem> clazz1 = null;
//        List<? extends FuncEnumDictItem> func = FuncEnumDictItem.getFunctions(clazz1, functions);
//        List<OAuth2ClientAuthenticationMethod> result = FuncEnumDictItem.getFunctions(OAuth2ClientAuthenticationMethod.class, functions);
//        return new HashSet<>(result);
//        T result ;
//        if (clazz.isAssignableFrom(List.class)) {
//            result = (T) result;
//        }
//        result = (T) new HashSet<>(result);
        return null;
    }
}
