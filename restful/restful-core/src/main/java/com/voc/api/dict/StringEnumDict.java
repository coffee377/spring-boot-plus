package com.voc.api.dict;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 23:29
 */
public interface StringEnumDict extends IDictItem<String>{
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
        return this.ordinal();
    }
}
