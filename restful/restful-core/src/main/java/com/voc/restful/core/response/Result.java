package com.voc.restful.core.response;

import com.voc.common.biz.IBizStatus;
import com.voc.common.bean.IBean;
import com.voc.common.biz.BizException;
import com.voc.restful.core.entity.BaseJsonEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Restful 接口响应结果
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/11/04 23:07
 */
@Getter
@SuppressWarnings("unchecked")
public class Result<T> extends BaseJsonEntity {

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

    public Result<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Result<T> setCode(long code) {
        this.code = code;
        return this;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <D> Builder<D> builder() {
        return new Builder<>();
    }

    public static <D> Builder<D> successBuilder(D data) {
        return (Builder<D>) builder().success().code(0).data(data);
    }

    public static <D> Result<D> success() {
        return (Result<D>) successBuilder(null).build();
    }

    public static <D> Result<D> success(D data) {
        return successBuilder(data).build();
    }

    public static <D> Builder<D> failureBuilder(long code, String message, D data) {
        return (Builder<D>) builder().failure(code, message).data(data);
    }

    public static <D> Result<D> failure(int code, String message, D data) {
        return failureBuilder(code, message, data).build();
    }

    public static <D> Result<D> failure(Exception exception) {
        return (Result<D>) builder().failure(exception).build();
    }

    public static <D> Result<D> failure(IBizStatus bizStatus) {
        return (Result<D>) builder().failure(bizStatus).build();
    }

    @Getter
    @Setter
    public static class Builder<D> implements IBean {

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
        private D data;

        /**
         * 成功结果
         *
         * @param message 提示信息
         * @param data    数据
         * @return Builder
         */
        public Builder<D> success(String message, D data) {
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
        public Builder<D> success(D data) {
            this.data = data;
            return this;
        }

        /**
         * 成功结果
         *
         * @return Builder
         */
        public Builder<D> success() {
            return this;
        }

        /**
         * 失败结果
         *
         * @param code    业务状态码
         * @param message 提示信息
         * @return Builder
         */
        public Builder<D> failure(long code, String message) {
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
        public Builder<D> failure(IBizStatus bizStatus) {
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
        public Builder<D> failure(Exception exception) {
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
        public Builder<D> code(long code) {
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
        public Builder<D> message(String message) {
            this.message = message;
            return this;
        }

        /**
         * 返回数据
         *
         * @param data 数据
         * @return Builder
         */
        public Builder<D> data(D data) {
            this.data = data;
            return this;
        }

        /**
         * 创建统一响应结果
         *
         * @return Result
         */
        public Result<D> build() {
            Result<D> result = new Result<>();
            result.setSuccess(this.success).setCode(this.code).setMessage(this.message).setData(this.data);
            return result;
        }

    }

}
