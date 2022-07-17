package com.voc.restful.core.response.impl;

import com.voc.common.biz.IBizStatus;
import org.springframework.http.HttpStatus;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/22 17:01
 */
public enum InternalBizStatus implements IBizStatus {

    /**
     * 操作成功返回值
     */
    OK("SUCCESS", HttpStatus.OK) {
        @Override
        public long getCode() {
            return 0L;
        }
    },

    OUT_OF_MODULE_CODE_RANGE("%s 超出模块编码范围 %s", HttpStatus.INTERNAL_SERVER_ERROR),
    OUT_OF_ERROR_CODE_RANGE("%s 超出错误编码范围 %s", HttpStatus.INTERNAL_SERVER_ERROR),

    UNAUTHORIZED("未经认证的用户", HttpStatus.UNAUTHORIZED),
    INVALID_BEARER_TOKEN("无效的令牌", HttpStatus.PRECONDITION_FAILED),

    ACCOUNT_EXPIRED("账户密钥已过期"),
    ACCOUNT_DISABLED("账户已被禁用"),
    ACCOUNT_LOCKED("账户已被锁定"),
    ACCOUNT_EXISTS("用户已存在"),
    USERNAME_NOT_FOUND("用户不存在"),
    BAD_CREDENTIALS("密钥凭证错误", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME_OR_PASSWORD("无效的用户名或密码", HttpStatus.BAD_REQUEST),
    OAUTH2_AUTHENTICATION_EXCEPTION("OAuth2 认证异常", HttpStatus.BAD_REQUEST),
    UNBOUND_USER("未绑定系统用户"),
    ALREADY_BOUND_USER("已绑定其他系统用户，请解绑后重新绑定"),
    FORBIDDEN("没有访问权限", HttpStatus.FORBIDDEN),
    NOT_FOUND("请求接口地址不存在", HttpStatus.NOT_FOUND),

    BAD_REQUEST("错误的请求", HttpStatus.BAD_REQUEST),
    INCORRECT_TEMPORARY_AUTHORIZATION_CODE("错误的临时授权码", HttpStatus.BAD_REQUEST),
    UN_SUPPORTED_METHOD("不支持的请求方法", HttpStatus.METHOD_NOT_ALLOWED),

    AUTHENTICATION_PROVIDER_NOT_FOUND("未找到相应的认证处理器"),
    INTERNAL_SERVER_ERROR("服务器内部错误"),
    JSON_SERIALIZE_EXCEPTION("序列化异常"),
    JSON_DESERIALIZE_EXCEPTION("反序列化异常"),

    ENTITY_VALIDATED_ERROR("实体属性校验错误", HttpStatus.BAD_REQUEST),
    REQUEST_ADDRESS_NOT_MATCH("请求地址与实体ID不一致"),
    INSERT_DATA_ERROR("新增数据异常"),
    UPDATE_DATA_ERROR("更新数据异常"),
    DELETE_DATA_ERROR("删除数据异常"),

    RECORD_EXISTS("已存在该条记录相关数据"),
    RECORD_NOT_EXISTS("指定记录不存在"),

    ;
    private String message;

    private final HttpStatus status;

    InternalBizStatus(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    InternalBizStatus(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public boolean internal() {
        return true;
    }

    @Override
    public int getModule() {
        return 1;
    }

    @Override
    public int getMask() {
        return this.ordinal();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public InternalBizStatus message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "[" + this.getCode() + "]" + this.message;
    }

}
