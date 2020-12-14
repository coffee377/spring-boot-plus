package com.voc.restful.core.autoconfigure.result;

import com.voc.restful.core.response.ErrorHandlerController;
import com.voc.restful.core.response.ResultAdvice;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 12:38
 * @see ErrorHandlerController
 * @see ResultAdvice
 */
@Configuration
@Import({ErrorHandlerController.class, ResultAdvice.class})
public class ResultAutoConfiguration {

}
