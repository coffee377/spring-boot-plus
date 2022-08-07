package com.voc.boot.result;

import com.voc.common.api.biz.BizException;
import com.voc.common.api.biz.InternalBizStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/24 01:02
 */
public class ResultTest {

    @Test
    void success() {
        Result<Object> success = Result.success();
        Assertions.assertTrue(success.isSuccess());
        Assertions.assertEquals(0, success.getCode());
        Assertions.assertEquals("ok", success.getMessage());
        Assertions.assertNull(success.getData());
        Assertions.assertNull(success.getTotal());
    }

    @Test
    void successData() {
        Result<List<Integer>> success = Result.success(Arrays.asList(1, 2, 3, 4, 5));
        Assertions.assertTrue(success.isSuccess());
        Assertions.assertEquals(0, success.getCode());
        Assertions.assertEquals("ok", success.getMessage());
        Assertions.assertNotNull(success.getData());
        Assertions.assertNull(success.getTotal());
    }

    @Test
    void successDataTotal() {
        Result<List<Integer>> success = Result.success(Arrays.asList(1, 2, 3, 4, 5), 10);
        Assertions.assertTrue(success.isSuccess());
        Assertions.assertEquals(0, success.getCode());
        Assertions.assertNull(success.getMessage());
        Assertions.assertNotNull(success.getData());
        Assertions.assertEquals(5, success.getData().size());
        Assertions.assertEquals(10, success.getTotal());
    }

    @Test
    void successMessageData() {
        Result<List<Integer>> success = Result.success("测试成功", Arrays.asList(1, 2, 3, 4, 5));
        Assertions.assertTrue(success.isSuccess());
        Assertions.assertEquals(0, success.getCode());
        Assertions.assertEquals("测试成功", success.getMessage());
        Assertions.assertNotNull(success.getData());
        Assertions.assertNull(success.getTotal());
    }

    @Test
    void successMessageDataTotal() {
        Result<List<Integer>> success = Result.success("测试成功", Arrays.asList(1, 2, 3, 4, 5), 20);
        Assertions.assertTrue(success.isSuccess());
        Assertions.assertEquals(0, success.getCode());
        Assertions.assertEquals("测试成功", success.getMessage());
        Assertions.assertNotNull(success.getData());
        Assertions.assertEquals(5, success.getData().size());
        Assertions.assertNotNull(success.getTotal());
        Assertions.assertEquals(20, success.getTotal());
    }

    @Test
    void failureCodeMessageData() {
        Result<String> failure = Result.failure(1, "测试异常提示", "测试异常数据");
        Assertions.assertFalse(failure.isSuccess());
        Assertions.assertNotEquals(0, failure.getCode());
        Assertions.assertNotEquals("ok", failure.getMessage());
        Assertions.assertNotNull(failure.getData());
    }

    @Test
    void failureCodeMessage() {
        Result<String> failure = Result.failure(1, "测试异常提示");
        Assertions.assertFalse(failure.isSuccess());
        Assertions.assertNotEquals(0, failure.getCode());
        Assertions.assertNotEquals("ok", failure.getMessage());
        Assertions.assertNull(failure.getData());
    }

    @Test
    void failureExceptionData() {
        Result<String> failure = Result.failure(new Exception("任意异常"), "测试异常类");
        Assertions.assertFalse(failure.isSuccess());
        Assertions.assertEquals(InternalBizStatus.INTERNAL_SERVER_ERROR.getCode(), failure.getCode());
        Assertions.assertEquals("任意异常", failure.getMessage());
        Assertions.assertNotNull(failure.getData());
    }

    @Test
    void failureException() {
        Result<String> failure = Result.failure(new BizException(InternalBizStatus.ACCOUNT_EXPIRED));
        Assertions.assertFalse(failure.isSuccess());
        Assertions.assertEquals(InternalBizStatus.ACCOUNT_EXPIRED.getCode(), failure.getCode());
        Assertions.assertEquals(InternalBizStatus.ACCOUNT_EXPIRED.getMessage(), failure.getMessage());
        Assertions.assertNull(failure.getData());
    }

    @Test
    void failureBizStatusData() {
        Result<String> failure = Result.failure(InternalBizStatus.FORBIDDEN, "123");
        Assertions.assertFalse(failure.isSuccess());
        Assertions.assertEquals(InternalBizStatus.FORBIDDEN.getCode(), failure.getCode());
        Assertions.assertEquals(InternalBizStatus.FORBIDDEN.getMessage(), failure.getMessage());
        Assertions.assertNotNull(failure.getData());
    }

    @Test
    void failureBizStatus() {
        Result<String> failure = Result.failure(InternalBizStatus.FORBIDDEN);
        Assertions.assertFalse(failure.isSuccess());
        Assertions.assertEquals(InternalBizStatus.FORBIDDEN.getCode(), failure.getCode());
        Assertions.assertEquals(InternalBizStatus.FORBIDDEN.getMessage(), failure.getMessage());
        Assertions.assertNull(failure.getData());
    }
}
