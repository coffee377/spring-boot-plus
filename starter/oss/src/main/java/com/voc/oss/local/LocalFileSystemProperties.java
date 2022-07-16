package com.voc.oss.local;

import com.voc.oss.BaseProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/19 19:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "oss.local")
public class LocalFileSystemProperties extends BaseProperties {

    /**
     * 存储本地文件的位置，默认 ~/upload
     */
    private String storagePath;

    /**
     * 静态资源映射路径，默认 /files/**
     */
    private String resourcePatten = "/files/**";

    public void setResourcePatten(String resourcePatten) {
        this.resourcePatten = resourcePatten;
        processEndpoint(resourcePatten);
    }

    public LocalFileSystemProperties(Boolean active) {
        super(active);
        processStoragePath();
        processEndpoint(resourcePatten);
    }

    public LocalFileSystemProperties() {
        this(false);
    }

    private void processStoragePath() {
        if (!StringUtils.hasText(storagePath)) {
            storagePath = System.getProperty("user.home") + File.separator + "upload";
        }
    }

    private void processEndpoint(String resourcePatten) {
        UriComponentsBuilder path =
                UriComponentsBuilder.newInstance()
                        .scheme("http")
                        .host("localhost").port("8080")
                        .path(resourcePatten.replace("/**", ""));
        this.setEndpoint(path.toUriString());
    }



}
