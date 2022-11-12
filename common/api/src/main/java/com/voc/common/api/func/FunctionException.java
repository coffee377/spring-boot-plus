package com.voc.common.api.func;

import com.voc.common.api.biz.BizException;
import com.voc.common.api.biz.IBizStatus;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/11 17:50
 */
public class FunctionException extends BizException {
    public FunctionException(String code, String message, int httpStatus, Object... formatArgs) {
        super(code, message, httpStatus, formatArgs);
    }

    public FunctionException(String code, String message, Object... formatArgs) {
        super(code, message, formatArgs);
    }

    public FunctionException(IBizStatus bizStatus, Object... formatArgs) {
        super(bizStatus, formatArgs);
    }
}
