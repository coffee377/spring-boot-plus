package com.voc.oss.local;

import com.voc.oss.ObjectStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.InputStream;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/19 17:39
 */
@Slf4j
public class LocalFileSystemStorageService implements ObjectStorageService {
    protected final LocalFileSystemProperties localFileSystemProperties;

    public LocalFileSystemStorageService(LocalFileSystemProperties localFileSystemProperties) {
        Assert.notNull(localFileSystemProperties.getEndpoint(), "endpoint can not be null");
        this.localFileSystemProperties = localFileSystemProperties;
    }

    @Override
    public void putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception {
        throw new RuntimeException("待实现");
    }

    @Override
    public void removeObject(String bucketName, String objectName) throws RuntimeException {
        throw new RuntimeException("待实现");
    }

    @Override
    public InputStream getObject(String bucketName, String objectName) throws RuntimeException {
        throw new RuntimeException("待实现");
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName) {
        Assert.hasText(objectName, "objectName can not be null");
        String endpoint = localFileSystemProperties.getEndpoint();
        String result = objectName;
        if (StringUtils.hasText(endpoint)) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint);
            builder.pathSegment(objectName);
            result = builder.build().toUriString();
        }

        log.debug("Object access address is: {} ", result);
        return result;
    }
}
