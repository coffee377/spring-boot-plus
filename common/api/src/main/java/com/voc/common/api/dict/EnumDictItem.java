package com.voc.common.api.dict;


import com.voc.common.api.authority.IAuthorities;
import com.voc.common.api.authority.IAuthorityDescriptor;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 枚举数据字典
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/06/27 22:37
 */
@SuppressWarnings("rawtypes")
public interface EnumDictItem<V> extends IDictItem<V>, IAuthorityDescriptor {

    /**
     * {@link Enum#ordinal()}
     *
     * @return 枚举序号, 如果枚举顺序改变, 此值将被变动
     */
    int ordinal();

    /**
     * the name of this enum constant
     * {@link Enum#name()}
     *
     * @return 枚举名称
     */
    String name();

    @Override
    default Integer getSort() {
        return ordinal();
    }

    /**
     * 枚举选项的描述,对一个选项进行详细的描述有时候是必要的.默认值为{@link #getText()}
     *
     * @return 描述
     */
    @Override
    default String getDescription() {
        return getText();
    }

    /**
     * 权限名称。默认值为{@link Enum#name()}
     *
     * @return String
     */
    @Override
    default String getName() {
        return name();
    }

    /**
     * 掩码值
     *
     * @return int
     */
    @Override
    default Integer getMask() {
        return ordinal();
    }

    /**
     * 对比是否和value相等,对比地址,值,value转为string忽略大小写对比,text忽略大小写对比
     * 与枚举名称是否相对（忽略大小写）
     *
     * @param value 值
     * @return 是否相等
     */
    default boolean eq(Object value) {
        return this == value
                || name().equalsIgnoreCase(String.valueOf(value))
                || getValue() == value
                || getValue().equals(value)
                || getValue().toString().equalsIgnoreCase(value.toString())
                || getText().equalsIgnoreCase(value.toString());
    }

    /**
     * 从指定的枚举类中查找想要的枚举,并返回一个{@link List},如果未找到,则返回一个{@link Collections#emptyList()}
     *
     * @param type      实现了{@link EnumDictItem}的枚举类
     * @param predicate 判断逻辑
     * @param <T>       枚举类型
     * @return 查找到的结果
     */
    static <T extends EnumDictItem> List<T> findByCondition(Class<T> type, Predicate<T> predicate) {
        if (type.isEnum()) {
            return Arrays.stream(type.getEnumConstants()).filter(predicate).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * 查找所有枚举值
     *
     * @param type 实现了{@link EnumDictItem}的枚举类
     * @param <T>  枚举类型
     * @return List<T>
     */
    static <T extends EnumDictItem> List<T> findAll(Class<T> type) {
        return findByCondition(type, item -> true);
    }


    /**
     * 查找权限中拥有的枚举值
     *
     * @param type      Class<T>
     * @param authority 枚举类型权限
     * @param <T>       枚举类型
     * @return List<T>
     */
    static <T extends EnumDictItem> List<T> getByAuthority(Class<T> type, IAuthorities authority) {
        BigInteger authorities = authority.get();
        if (authorities != null) {
            return new ArrayList<>(findByCondition(type, item -> item.get().or(authorities).equals(authorities)));
        }
        return Collections.emptyList();
    }

    /**
     * 根据枚举的{@link EnumDictItem#getValue()}来查找.
     *
     * @param type  Class<T>
     * @param value BigInteger
     * @param <T>   枚举类型
     * @return 查找到的结果
     * @see this#findByCondition(Class, Predicate)
     */
    static <T extends EnumDictItem> Optional<T> findByValue(Class<T> type, Object value) {
        return findByCondition(type, item -> item.getValue().equals(value)).stream().findFirst();
    }

    /**
     * 根据枚举的{@link EnumDictItem#getText()}  来查找.
     *
     * @param type Class<T>
     * @param text String
     * @param <T>  枚举类型
     * @return 查找到的结果
     * @see this#findByCondition(Class, Predicate)
     */
    static <T extends EnumDictItem> Optional<T> findByText(Class<T> type, String text) {
        return findByCondition(type, item -> item.getText().equalsIgnoreCase(text)).stream().findFirst();
    }

    /***
     * 根据枚举的{@link EnumDictItem#getValue()},{@link EnumDictItem#getText()}来查找.
     * @param type Class<T>
     * @param target Object
     * @param <T> 枚举类型
     * @return 查找到的结果
     * @see this#findByCondition(Class, Predicate)
     */
    static <T extends EnumDictItem> Optional<T> find(Class<T> type, Object target) {
        return findByCondition(type, item -> item.eq(target)).stream().findFirst();
    }

}
