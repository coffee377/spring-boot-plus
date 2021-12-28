package com.voc.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 13:32
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class User {
    private String username;

    private Integer age;
}
