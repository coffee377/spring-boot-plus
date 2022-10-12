package com.voc.boot.result.response.impl;

import com.voc.boot.result.Result;
import com.voc.boot.result.response.IResponseHandler;
import com.voc.common.api.biz.IBizStatus;
import com.voc.common.api.biz.InternalBizStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/25 09:34
 */
@Slf4j
public class ResponseHandler implements IResponseHandler, ApplicationContextAware {
    private Result result;
    private IBizStatus bizStatus = InternalBizStatus.OK;

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public Result getResult() {
        if (result == null) {
            long code = bizStatus.getCode();
            return code == 0 ? Result.success() : Result.failure(bizStatus);
        }
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public IBizStatus getBizStatus() {
        return bizStatus;
    }

    public void setBizStatus(IBizStatus bizStatus) {
        this.bizStatus = bizStatus;
    }

    @Override
    public void beforeWrite(HttpServletResponse response) throws IOException {
        Assert.notNull(bizStatus, "bizStatus must be set");
        if (log.isDebugEnabled()) {
            log.debug("响应 JSON 数据为：{}", getResult());
        }
        IResponseHandler.super.beforeWrite(response);
    }
}
