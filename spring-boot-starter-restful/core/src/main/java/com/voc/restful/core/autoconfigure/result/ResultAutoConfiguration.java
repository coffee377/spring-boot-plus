package com.voc.restful.core.autoconfigure.result;

import com.voc.restful.core.response.ErrorHandlerController;
import com.voc.restful.core.response.ResultAdvice;
import com.voc.restful.core.util.SpringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 12:38
 */
@Configuration
@Import({ErrorHandlerController.class, ResultAdvice.class, SpringUtil.class})
public class ResultAutoConfiguration {

}
