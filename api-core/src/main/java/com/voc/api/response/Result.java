package com.voc.api.response;

import com.voc.system.entity.JsonEntity;
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
public class Result extends JsonEntity {

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

    public static ResultBuilder builder() {
        return new ResultBuilder();
    }

    public static ResultBuilder successBuilder(Object data) {
        return builder().success().code(0).data(data);
    }

    public static Result success(Object data) {
        return successBuilder(data).build();
    }

    public static ResultBuilder failureBuilder(int code, String message, Object data) {
        return builder().failure().code(code).message(message).data(data);
    }

    public static Result failure(int code, String message, Object data) {
        return failureBuilder(code, message, data).build();
    }

    public static Result failure(Exception exception) {
        return builder().exception(exception).build();
    }

    public static Result failure(IBizError bizError) {
        return builder().error(bizError).build();
    }

}
