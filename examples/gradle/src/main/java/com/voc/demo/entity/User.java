package com.voc.demo.entity;

import com.voc.restful.core.entity.JsonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 15:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class User extends JsonEntity {

    private String name;

    private Integer age;

    private String sex;

    private User[] users;

}
