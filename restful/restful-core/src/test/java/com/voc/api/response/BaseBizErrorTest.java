package com.voc.api.response;

import com.voc.restful.core.response.impl.InternalBizStatus;
import org.junit.jupiter.api.Test;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/09 14:54
 */
class BaseBizErrorTest {

    @Test
    void getCode() {
        InternalBizStatus[] values = InternalBizStatus.values();
        for (InternalBizStatus value : values) {
            System.out.println(value.getCode() + " ==== " + value.getMessage());
        }
    }
}
