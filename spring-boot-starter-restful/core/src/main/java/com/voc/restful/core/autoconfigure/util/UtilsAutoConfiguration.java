package com.voc.restful.core.autoconfigure.util;

import com.voc.restful.core.util.SpringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/14 11:51
 * @see SpringUtils
 */
@Configuration
@Import({SpringUtils.class})
public class UtilsAutoConfiguration {

}
