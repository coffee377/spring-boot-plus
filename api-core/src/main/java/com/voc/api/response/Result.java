package com.voc.api.response;

import com.voc.api.bean.IBean;
import com.voc.system.entity.JsonEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

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
public class Result<T> extends JsonEntity {

    /**
     * 是否成功
     */
    private boolean success = false;

    /**
     * 状态码
     */
    private long code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public Result() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder successBuilder(Object data) {
        return builder().success().code(0).data(data);
    }

    public static Result success(Object data) {
        return successBuilder(data).build();
    }

    public static Builder failureBuilder(long code, String message, Object data) {
        return builder().failure(code, message).data(data);
    }

    public static Result failure(int code, String message, Object data) {
        return failureBuilder(code, message, data).build();
    }

    public static Result failure(Exception exception) {
        return builder().failure(exception).build();
    }

    public static Result failure(IBizStatus bizStatus) {
        return builder().failure(bizStatus).build();
    }

    @Getter
    @Setter
    public static class Builder implements IBean {

        /**
         * 是否成功
         */
        private boolean success = true;

        /**
         * 业务状态码
         */
        private long code = 0L;

        /**
         * 业务提示信息
         */
        private String message = "ok";

        /**
         * 数据
         */
        private Object data;

        /**
         * 成功结果
         *
         * @param message 提示信息
         * @param data    数据
         * @return Builder
         */
        public Builder success(String message, Object data) {
            this.message = message;
            this.data = data;
            return this;
        }

        /**
         * 成功结果
         *
         * @param data 数据
         * @return Builder
         */
        public Builder success(Object data) {
            this.data = data;
            return this;
        }

        /**
         * 成功结果
         *
         * @return Builder
         */
        public Builder success() {
            return this;
        }

        /**
         * 失败结果
         *
         * @param code    业务状态码
         * @param message 提示信息
         * @return Builder
         */
        public Builder failure(long code, String message) {
            this.success = false;
            this.code = code;
            this.message = message;
            return this;
        }

        /**
         * 失败结果
         *
         * @param bizStatus 业务状态
         * @return Builder
         */
        public Builder failure(IBizStatus bizStatus) {
            this.success = false;
            this.code = bizStatus.getCode();
            this.message = bizStatus.getMessage();
            return this;
        }

        /**
         * 异常结果
         *
         * @param exception Exception
         * @return Builder
         */
        public Builder failure(Exception exception) {
            this.success = false;
            if (exception instanceof BizException) {
                BizException bizException = (BizException) exception;
                this.code = bizException.getCode();
                this.message = bizException.getMessage();
            } else {
                this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
                this.message = exception.getMessage();
            }
            return this;
        }

        /**
         * 状态码
         *
         * @param code 业务状态码
         * @return Builder
         */
        public Builder code(long code) {
            this.code = code;
            this.success = code == 0L;
            return this;
        }

        /**
         * 提示信息
         *
         * @param message 信息
         * @return Builder
         */
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * 返回数据
         *
         * @param data 数据
         * @return Builder
         */
        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        /**
         * 创建统一响应结果
         *
         * @return Result
         */
        public Result<Object> build() {
            Result<Object> result = new Result<>();
            result.setSuccess(this.success);
            result.setCode(this.code);
            result.setMessage(this.message);
            result.setData(this.data);
            return result;
        }

    }

}
