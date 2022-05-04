package com.voc.restful.core.response.impl;

import com.voc.restful.core.response.IBizStatus;
import com.voc.restful.core.response.IResponseHandler;
import com.voc.restful.core.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/25 09:34
 */
@Slf4j
public class ResponseHandler implements IResponseHandler {
    private Result result;
    private IBizStatus bizStatus = BaseBizStatus.OK;

    @Override
    public Result getResult() {
        if (result == null) {
            long code = bizStatus.getCode();
            return code > 0 ? Result.failure(bizStatus) : Result.success();
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
            log.debug("响应 JSON 数据为：{}", getResult().toJson());
        }
        IResponseHandler.super.beforeWrite(response);
    }
}
