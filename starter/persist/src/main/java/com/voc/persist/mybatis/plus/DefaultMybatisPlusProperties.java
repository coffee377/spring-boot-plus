package com.voc.persist.mybatis.plus;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/04 16:54
 */
@Slf4j
public class DefaultMybatisPlusProperties implements MybatisPlusPropertiesCustomizer {

    @Override
    public void customize(MybatisPlusProperties properties) {
        log.debug("{}", properties);

        /* TypeHandlersPackage */
        String typeHandlersPackage = properties.getTypeHandlersPackage();
        Set<String> packages = new HashSet<>(1);
        packages.add("com.voc.persist.mybatis.handler");
        if (StringUtils.hasText(typeHandlersPackage)){
            String[] split = StringUtils.split(typeHandlersPackage, ",");
            assert split != null;
            packages.addAll(Arrays.asList(split));
        }
        String join = String.join(",", packages);
        properties.setTypeHandlersPackage(join);

        /* 关闭 logo 打印 */
        properties.getGlobalConfig().setBanner(false);
    }
}
