package com.voc.restful.core.autoconfigure.json;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 10:33
 */
public interface IZoneInfo {

    /**
     * 获取地区偏移量
     *
     * @return ZoneOffset
     */
    default ZoneOffset getZoneOffset() {
        return ZoneId.systemDefault().getRules().getOffset(Instant.now());
    }

    /**
     * 获取默认地区
     *
     * @return ZoneId
     */
    default ZoneId getZoneId() {
        return ZoneId.systemDefault();
    }

}
