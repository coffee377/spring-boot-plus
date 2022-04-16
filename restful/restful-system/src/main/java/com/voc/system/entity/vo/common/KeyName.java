package com.voc.system.entity.vo.common;

import com.voc.restful.core.vo.JsonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 22:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KeyName extends JsonVO {
    /**
     * 标签标识
     */
    private String key;

    /**
     * 标签名称
     */
    private String name;
}
