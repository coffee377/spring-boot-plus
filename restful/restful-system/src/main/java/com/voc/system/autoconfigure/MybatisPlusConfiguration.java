package com.voc.system.autoconfigure;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/17 12:56
 */
@Configuration
public class MybatisPlusConfiguration {

    class dfdf implements ConfigurationCustomizer {
        @Override
        public void customize(MybatisConfiguration configuration) {
//            configuration.sett
//            configuration.getTypeHandlerRegistry().register();
        }
    }
}
