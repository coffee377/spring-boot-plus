package com.voc.security.autoconfigure;

import com.voc.security.autoconfigure.config.AuthorizationServerConfiguration;
import com.voc.security.autoconfigure.config.DefaultSecurityConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/14 08:25
 */
public class AuthorizationServerImport implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        List<Class> classNames = new ArrayList<>(4);

        classNames.add(DefaultSecurityConfiguration.class);
        classNames.add(AuthorizationServerConfiguration.class);
//        classNames.add(TokenConfiguration.class);

        return classNames.stream().map(Class::getName).collect(Collectors.toSet()).toArray(new String[]{});
    }

}
