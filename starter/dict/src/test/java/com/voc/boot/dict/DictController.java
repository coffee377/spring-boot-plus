package com.voc.boot.dict;

import com.voc.boot.dict.persist.DictItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 22:39
 */
@RestController
@RequestMapping(value = "/dict",name = "字典")
public class DictController {

    @GetMapping(value = "/enum",name = "枚举字典序列化")
    public User enumDictItem() {
        return User.builder().id("1").name("coffee377")
                .sex(Sex.FEMALE)
                .sex1(Sex.FEMALE)
                .sex2(Sex.FEMALE)
                .sex3(Sex.FEMALE)
                .sex4(Sex.FEMALE)
                .build();
    }

    @GetMapping(value = "/data",name = "持久化字典序列化")
    public DictItem<Object> dataDictItem() {
        return DictItem.builder().id("1").value(1).label("测试").description("测试").build();
    }
}
