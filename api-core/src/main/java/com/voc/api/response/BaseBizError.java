package com.voc.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/22 17:01
 */
@Getter
@AllArgsConstructor
public enum BaseBizError implements IBizError {

    /**
     * 操作成功返回值
     */
    OK(0, "SUCCESS"),

    BAD_REQUEST(400, "错误的请求"),
    UNAUTHORIZED(401, "未经认证的用户"),
    FORBIDDEN(403, "没有访问权限"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

//    ENTITY_VALIDATED_ERROR(1000, "实体属性校验错误"),
//    REQUEST_ADDRESS_NOT_MATCH(1001, "请求地址与实体ID不一致"),
//    INSERT_DATA_ERROR(1002, "插入数据异常"),
//    UPDATE_DATA_ERROR(1003, "更新数据异常"),
//    DELETE_DATA_ERROR(1004, "删除数据异常"),
//
//    USER_ALREADY_EXISTS(10001, "用户已存在"),
//    USER_NOT_EXISTS(10002, "用户不存在"),
//    BAD_CREDENTIALS(10003, "密钥凭证错误"),
//    INVALID_USERNAME_OR_PASSWORD(10004, "无效的用户名或密码"),
//    RECORD_EXISTS(10005, "已存在该条记录相关数据"),
//    RECORD_NOT_EXISTS(10006, "指定记录不存在"),
//    AREA_NOT_FIND(10007, "地区匹配不存在"),
//    ORGAN_NOT_FIND(10008, "机构匹配不存在"),
//    DATA_NOT_FIND(10009, "数据查询错误");

    private final int code;

    private final String message;

    @Override
    public String toString() {
        return "[" + this.code + "]" + this.message;
    }

}
