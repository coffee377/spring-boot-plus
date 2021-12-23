package com.voc.api.dict;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/06/27 22:37
 */
public interface EnumDict extends ItemDefine<String> {

    /**
     * {@link Enum#ordinal()}
     *
     * @return 枚举序号, 如果枚举顺序改变, 此值将被变动
     */
    int ordinal();

    /**
     * 索引
     *
     * @return 序号
     */
    @Override
    default Integer getIndex() {
        return ordinal();
    }

    /**
     * 枚举选项的值,通常由字母或者数字组成,
     * 并且在同一个枚举中值唯一;对应数据库中的值通常也为此值
     *
     * @return 枚举的值
     * @see ItemDefine#getValue()
     */
    @Override
    default String getValue() {
        return this.getMask().toString();
    }

    /**
     * 枚举字典选项的文本,通常为中文
     *
     * @return 枚举的文本
     * @see ItemDefine#getText()
     */
    @Override
    String getText();

    /**
     * 枚举选项的描述,对一个选项进行详细的描述有时候是必要的.默认值为{@link this#getText()}
     *
     * @return 描述
     */
    @Override
    default String getComments() {
        return getText();
    }

    /**
     * 下级字典项
     *
     * @return List<ItemDefine < BigInteger>>
     */
    @Override
    default List<ItemDefine<String>> getChildren() {
        return null;
    }

    /**
     * 掩码值
     *
     * @return long
     */
    default BigInteger getMask() {
        return BigInteger.ONE.shiftLeft(this.ordinal());
    }

    /**
     * 对比是否和value相等,对比地址,值,value转为string忽略大小写对比,text忽略大小写对比
     *
     * @param value 值
     * @return 是否相等
     */
    default boolean eq(Object value) {
        return this == value
                || getValue() == value
                || getValue().equals(value)
                || getMask().equals(value)
                || String.valueOf(getValue()).equalsIgnoreCase(String.valueOf(value))
                || getText().equalsIgnoreCase(String.valueOf(value));
    }

    /**
     * 查找所有枚举值
     *
     * @param type 实现了{@link EnumDict}的枚举类
     * @param <T>  枚举类型
     * @return List<T>
     */
    static <T extends EnumDict> List<T> findAll(Class<T> type) {
        return findByCondition(type, item -> true);
    }

    /**
     * 从指定的枚举类中查找想要的枚举,并返回一个{@link List},如果未找到,则返回一个{@link Collections#emptyList()}
     *
     * @param type      实现了{@link EnumDict}的枚举类
     * @param predicate 判断逻辑
     * @param <T>       枚举类型
     * @return 查找到的结果
     */
    static <T extends EnumDict> List<T> findByCondition(Class<T> type, Predicate<T> predicate) {
        if (type.isEnum()) {
            return Arrays.stream(type.getEnumConstants())
                    .filter(predicate)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * 查找包含的所有枚举值
     *
     * @param type  Class<T>
     * @param masks 枚举掩码之和
     * @param <T>   枚举类型
     * @return List<T>
     */
    static <T extends EnumDict> List<T> contains(Class<T> type, IMasks masks) {
        BigInteger maskSum = masks.get();
        if (maskSum != null) {
            return new ArrayList<>(findByCondition(type, item -> item.getMask().or(maskSum).equals(maskSum)));
        }
        return Collections.emptyList();
    }

    /**
     * 根据枚举的{@link EnumDict#getValue()}来查找.
     *
     * @param type  Class<T>
     * @param value BigInteger
     * @param <T>   枚举类型
     * @return 查找到的结果
     * @see this#findByCondition(Class, Predicate)
     */
    static <T extends EnumDict> Optional<T> findByValue(Class<T> type, String value) {
        return findByCondition(type, item -> item.getValue().equals(value)).stream().findFirst();
    }

    /**
     * 根据枚举的{@link EnumDict#getText()}  来查找.
     *
     * @param type Class<T>
     * @param text String
     * @param <T>  枚举类型
     * @return 查找到的结果
     * @see this#findByCondition(Class, Predicate)
     */
    static <T extends EnumDict> Optional<T> findByText(Class<T> type, String text) {
        return findByCondition(type, item -> item.getText().equalsIgnoreCase(text)).stream().findFirst();
    }

    /***
     * 根据枚举的{@link EnumDict#getValue()},{@link EnumDict#getText()}来查找.
     * @param type Class<T>
     * @param target Object
     * @param <T> 枚举类型
     * @return 查找到的结果
     * @see this#findByCondition(Class, Predicate)
     */
    static <T extends EnumDict> Optional<T> find(Class<T> type, Object target) {
        return findByCondition(type, item -> item.eq(target)).stream().findFirst();
    }

}
