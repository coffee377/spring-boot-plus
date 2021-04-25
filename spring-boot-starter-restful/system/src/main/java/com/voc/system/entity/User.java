package com.voc.system.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:10
 */
@Getter
@Setter
public class User implements IUser<String> {

    private String id;

    private String username;

    private String password;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
