package com.voc.restful.core.autoconfigure.json;

import com.voc.restful.core.autoconfigure.json.annotation.Temporal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/20 15:24
 */
@Slf4j
public class TemporalTypeFilter implements TypeFilter {

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        AnnotationMetadata metadata = metadataReader.getAnnotationMetadata();
        boolean hasAnnotation = metadata.hasAnnotation(Temporal.class.getName());
        log.debug("{} => {}", metadata.getClassName(), hasAnnotation);
        return hasAnnotation;
    }

}
