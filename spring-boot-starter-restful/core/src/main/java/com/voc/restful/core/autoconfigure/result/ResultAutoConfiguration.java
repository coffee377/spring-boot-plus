package com.voc.restful.core.autoconfigure.result;

import com.voc.restful.core.response.ErrorHandlerController;
import com.voc.restful.core.response.ResultAdvice;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置需要在 ErrorMvcAutoConfiguration 配置前进行，否则无法覆盖默认 BasicErrorController
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 12:38
 * @see ErrorHandlerController
 * @see ResultAdvice
 * @see ErrorMvcAutoConfiguration#basicErrorController(ErrorAttributes, ObjectProvider)
 */
@Configuration
@Import({ErrorHandlerController.class, ResultAdvice.class})
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class ResultAutoConfiguration {

}
