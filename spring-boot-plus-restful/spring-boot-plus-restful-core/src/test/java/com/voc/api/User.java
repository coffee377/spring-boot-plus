package com.voc.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/31 22:47
 */
@Data
@AllArgsConstructor
public class User {

    private  String name;

    private Integer age;

    private LocalDate birthday;

    private Instant time;

    private String address;




}
