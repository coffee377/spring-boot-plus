package com.voc.system.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:10
 */
@Getter
@Setter
@Document(collection = "sys_user")
public class User implements IUser<String> {

    private String id;

    private String username;

    private String password;

    private String avatar;

    private String realName;

    private Integer status;

}
