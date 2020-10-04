package com.voc.api.response;

import com.voc.api.bean.IBean;
import org.springframework.http.HttpStatus;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/04 13:50
 */
public class ResultBuilder implements IBean {

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
    private String message = "ok";

    /**
     * 响应数据
     */
    private Object data;

    public ResultBuilder success() {
        this.success = true;
        return this;
    }

    public ResultBuilder failure() {
        this.success = false;
        return this;
    }

    public ResultBuilder code(int code) {
        this.code = code;
        this.success = code == 0;
        return this;
    }

    public ResultBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ResultBuilder data(Object data) {
        this.data = data;
        return this;
    }

    public ResultBuilder exception(Exception e) {
        this.success = false;
        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            this.code = bizException.getCode();
            this.message = bizException.getMessage();
        } else {
            this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            this.message = e.getMessage();
        }
        return this;
    }

    public ResultBuilder error(IBizError bizError) {
        this.success = false;
        this.code = bizError.getCode();
        this.message = bizError.getMessage();
        return this;
    }

    public Result build() {
        return this.create();
    }

    private Result create() {
        Result result = new Result();
        result.setSuccess(this.success);
        result.setCode(this.code);
        result.setMessage(this.message);
        result.setData(this.data);
        return result;
    }

}
