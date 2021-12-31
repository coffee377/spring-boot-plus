package com.voc.restful.core.autoconfigure.json.gson;

import com.voc.restful.core.autoconfigure.json.annotation.Temporal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/31 17:42
 */
@Slf4j
public class GsonImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        try {
            String packageName = Class.forName(annotationMetadata.getClassName()).getPackage().getName();
            ClassPathScanningCandidateComponentProvider scanner
                    = new ClassPathScanningCandidateComponentProvider(false);
            TypeFilter typeFilter = new AnnotationTypeFilter(Temporal.class);
            scanner.addIncludeFilter(typeFilter);
            annotationMetadata.hasAnnotation(Temporal.class.getCanonicalName());
            return scanner.findCandidateComponents(packageName)
                    .stream().map(BeanDefinition::getBeanClassName).collect(Collectors.toList()).toArray(new String[]{});
        } catch (ClassNotFoundException e) {
            if (log.isErrorEnabled()) {
                log.error("{}", e.getMessage());
            }
        }
        return new String[0];
    }
}
