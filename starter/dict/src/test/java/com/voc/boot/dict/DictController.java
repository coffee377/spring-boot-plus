package com.voc.boot.dict;

import com.fasterxml.jackson.annotation.JsonProperty;
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

        @DictItemSerialize(SerializeType.OBJECT)
        private Sex sex;
    }


    @GetMapping("/enum")
    @DictItemSerialize(type = SerializeType.TEXT)
    public Object enumDictItem() {
//        return Sex.FEMALE;
        return User.builder().id("111").sex(Sex.FEMALE).build();
    }

    @GetMapping("/data")
    public DictItem<Object> dataDictItem() {
        DictItem<Object> build = DictItem.builder().id("1").value(1).text("测试").description("测试").build();
        return build;
    }
}
