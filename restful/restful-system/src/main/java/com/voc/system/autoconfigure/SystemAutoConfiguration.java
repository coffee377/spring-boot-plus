package com.voc.system.autoconfigure;

import com.voc.system.SystemProperties;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 17:07
 */

@Configuration
@EnableConfigurationProperties(SystemProperties.class)
@ComponentScan(basePackages = {"com.voc.system"})
@MapperScan("com.voc.system.dao")
@AutoConfigureBefore({SecurityAutoConfiguration.class})
public class SystemAutoConfiguration {
    @Bean
    public OpenAPI sysOpenApi() {
        return new OpenAPI()
                .info(new Info().title("系统管理 API")
                        .description("SpringDoc API 演示")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://github.com/macrozheng/mall-learning"))
                ).servers(Arrays.asList())
                .externalDocs(new ExternalDocumentation()
                        .description("SpringBoot实战电商项目mall（50K+Star）全套文档")
                        .url("http://www.macrozheng.com"));
    }

//    @Bean
//    public GroupedOpenApi emailPluginOpenApi() {
//        SpringDocUtils.getConfig().addRestControllers(UserController.class);
//        String[] packages = new String[]{"com.voc"};
//        return GroupedOpenApi.builder().group("plugins").packagesToScan(packages).build();
//    }
}
