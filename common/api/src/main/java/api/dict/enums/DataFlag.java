package api.dict.enums;

import api.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据标识（增删改标识）
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/12 08:56
 */
@Getter
@AllArgsConstructor
public enum DataFlag implements EnumDictItem<Integer> {

    ADD(1, "新增"),
    DELETE(-1, "删除"),
    MODIFY(0, "修改");

    private final Integer value;
    private final String text;

}
