package com.voc.api.response;

import com.voc.api.bean.IBean;
import com.voc.api.entity.JsonEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 响应数据封装
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/11/04 23:07
 */
@Getter
@Setter
public class Result extends JsonEntity implements IBean {

    /**
     * 是否成功
     */
    private boolean success = false;

    /**
     * 响应编码
     */
    private int code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private Object data;

    private Result() {
    }

    /**
     * 响应结果
     *
     * @param code    错误编码
     * @param message 错误信息
     * @param data    响应数据
     * @return Result
     */
    public static Result of(int code, String message, Object data) {
        Result item = new Result();
        item.setSuccess(code == 0);
        item.setCode(code);
        item.setMessage(message);
        item.setData(data);
        return item;
    }

    /**
     * 响应结果
     *
     * @param bizError IBizError
     * @param data     响应数据
     * @return Result<Object>
     */
    public static Result of(IBizError bizError, Object data) {
        return of(bizError.getCode(), bizError.getMessage(), data);
    }

    /**
     * 成功时响应
     *
     * @param data 数据
     * @return Result>
     */
    public static Result success(Object data) {
        return of(BaseBizError.OK, data);
    }

    /**
     * 成功时响应
     *
     * @return Result>
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 错误时响应
     *
     * @param bizError 业务错误
     * @param data     数据
     * @return Result
     */
    public static Result failure(IBizError bizError, Object data) {
        return of(bizError, data);
    }

    /**
     * 错误时响应
     *
     * @return Result<Object>
     */
    public static Result failure(IBizError bizError) {
        return failure(bizError, null);
    }

}
