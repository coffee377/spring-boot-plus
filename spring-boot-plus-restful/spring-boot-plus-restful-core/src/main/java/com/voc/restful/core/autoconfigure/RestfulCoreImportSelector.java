package com.voc.restful.core.autoconfigure;

import com.voc.restful.core.controller.PingController;
import com.voc.restful.core.util.SpringUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/31 16:11
 */
public class RestfulCoreImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return importClasses().stream().map(Class::getCanonicalName)
                .collect(Collectors.toList()).toArray(new String[]{});
    }

    public List<Class<?>> importClasses() {
        return Arrays.asList(SpringUtils.class, PingController.class);
    }
}
