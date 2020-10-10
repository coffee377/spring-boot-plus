package com.voc.api.response;

import com.voc.api.utils.BitUtil;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/22 17:01
 */
public enum BaseBizError implements IBizError  {

    /**
     * 操作成功返回值
     */
    OK("SUCCESS", HttpStatus.OK) {
        @Override
        public int getCode() {
            return 0;
        }
    },

    BAD_REQUEST("错误的请求", HttpStatus.BAD_REQUEST),
    UN_SUPPORTED_METHOD("不支持的请求方法", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("未经认证的用户", HttpStatus.UNAUTHORIZED),
    ACCOUNT_EXPIRED("账户密钥已过期", HttpStatus.UNAUTHORIZED),
    ACCOUNT_DISABLED("账户已被禁用", HttpStatus.UNAUTHORIZED),
    ACCOUNT_LOCKED("账户已被锁定", HttpStatus.UNAUTHORIZED),
    ACCOUNT_EXISTS("用户已存在", HttpStatus.UNAUTHORIZED),
    USERNAME_NOT_FOUND("用户不存在", HttpStatus.UNAUTHORIZED),
    BAD_CREDENTIALS("密钥凭证错误", HttpStatus.UNAUTHORIZED),
    INVALID_USERNAME_OR_PASSWORD("无效的用户名或密码", HttpStatus.UNAUTHORIZED),
    INVALID_BEARER_TOKEN("无效的访问令牌", HttpStatus.UNAUTHORIZED),

    FORBIDDEN("没有访问权限", HttpStatus.FORBIDDEN),

    INTERNAL_SERVER_ERROR("服务器内部错误", HttpStatus.INTERNAL_SERVER_ERROR);

    //    ENTITY_VALIDATED_ERROR(1000, "实体属性校验错误"),
//    REQUEST_ADDRESS_NOT_MATCH(1001, "请求地址与实体ID不一致"),
//    INSERT_DATA_ERROR(1002, "插入数据异常"),
//    UPDATE_DATA_ERROR(1003, "更新数据异常"),
//    DELETE_DATA_ERROR(1004, "删除数据异常"),
//


//    RECORD_EXISTS(10005, "已存在该条记录相关数据"),
//    RECORD_NOT_EXISTS(10006, "指定记录不存在"),
//    AREA_NOT_FIND(10007, "地区匹配不存在"),
//    ORGAN_NOT_FIND(10008, "机构匹配不存在"),
//    DATA_NOT_FIND(10009, "数据查询错误");

    private final String message;

    private final HttpStatus status;

    BaseBizError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public int getCode() {
        /* 模块编码 */
        int module = BitUtil.multiply(1, 1000);
        return BitUtil.add(module, this.ordinal());
    }

    @Override
    public String getMessage() {
        return message;
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
