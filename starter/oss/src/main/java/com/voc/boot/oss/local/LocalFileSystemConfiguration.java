package com.voc.boot.oss.local;

import com.voc.boot.oss.OSSProperties;
import com.voc.boot.oss.ObjectStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/19 18:57
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "oss.local", name = "active", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(LocalFileSystemProperties.class)
public class LocalFileSystemConfiguration {

    @Bean
    @ConditionalOnMissingBean
    ObjectStorageService objectStorageService(LocalFileSystemProperties localFileSystemProperties) {
        return new LocalFileSystemStorageService(localFileSystemProperties);
    }

    @Configuration
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    static class LocalFileResourceHandler implements WebMvcConfigurer {

        private final OSSProperties oss;

        public LocalFileResourceHandler(OSSProperties oss) {
            this.oss = oss;
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            OSSProperties.StorageType storageType = oss.getStorageType();
            /* 本地文件存储 */
            if (OSSProperties.StorageType.LOCAL.equals(storageType)) {
                LocalFileSystemProperties local = oss.getLocal();
                if (local.isActive()) {
                    log.debug("The local file storage system has been activated");
                    String pathPatten = local.getResourcePatten();
                    String storagePath = local.getStoragePath();
                    if (!storagePath.endsWith(File.separator)) {
                        storagePath += File.separator;
                    }
                    FileSystemResource resource = new FileSystemResource(storagePath);
                    registry.addResourceHandler(pathPatten).addResourceLocations(resource);
                }
            }
        }
    }
}
