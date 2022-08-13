package com.voc.boot.dict;

import com.voc.boot.dict.json.jackson.DictItemSerialize;
import com.voc.boot.dict.json.jackson.SerializeType;
import com.voc.boot.dict.persist.DictItem;
import com.voc.common.api.dict.enums.UsingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 22:39
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Data
    @Builder
    @AllArgsConstructor
    private static class User {
        private String id;

        @DictItemSerialize(type = SerializeType.OBJECT)
        private UsingStatus status;
    }


    @GetMapping("/enum")
    public User enumDictItem() {
        return User.builder().id("1").status(UsingStatus.LOCK).build();
    }

    @GetMapping("/data")
    public DictItem<Object> dataDictItem() {
        DictItem<Object> build = DictItem.builder().id("1").value(1).text("测试").description("测试").build();
        return build;
    }
}
