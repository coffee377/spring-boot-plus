package com.voc.restful.core.entity.impl;

import com.voc.restful.core.entity.JsonEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 认证表单实体
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/19 16:34
 */
@Getter
@Setter
public class Certification extends JsonEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否记住我
     */
    private boolean rememberMe;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Certification that = (Certification) o;
        return rememberMe == that.rememberMe &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, rememberMe);
    }

}
