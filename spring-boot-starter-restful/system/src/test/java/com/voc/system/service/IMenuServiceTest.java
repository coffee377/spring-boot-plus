package com.voc.system.service;

import com.voc.restful.core.response.impl.BaseBizStatus;
import com.voc.restful.core.response.BizException;
import com.voc.system.entity.impl.Menu;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/08 11:26
 */
@Slf4j
@SpringBootTest
class IMenuServiceTest {

    @Resource
    IMenuService menuService;

    @Test
    public void findById() {
        BizException bizException = Assertions.assertThrows(BizException.class, () -> {
            Menu menu = menuService.findById("-1");
        });
        Assertions.assertEquals(bizException.getMessage(), BaseBizStatus.RECORD_NOT_EXISTS.getMessage());
    }
}
