package com.voc.boot.oss;

import com.voc.boot.oss.local.LocalFileSystemProperties;
import com.voc.boot.oss.aliyun.AliyunProperties;
import com.voc.boot.oss.minio.MinioProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/19 17:41
 */
@Data
@ConfigurationProperties(prefix = "oss")
public class OSSProperties {

    /**
     * 对称存储类型
     */
    private StorageType storageType = StorageType.LOCAL;

    /**
     * 本地文件存储配置
     */
    @NestedConfigurationProperty
    private LocalFileSystemProperties local = new LocalFileSystemProperties(true);

    /**
     * Minio对象存储配置
     */
    @NestedConfigurationProperty
    private MinioProperties minio = new MinioProperties();

    /**
     * 阿里云对象存储配置
     */
    @NestedConfigurationProperty
    private AliyunProperties aliyun = new AliyunProperties();

    public enum StorageType {
        LOCAL,
        MINIO,
        ALIYUN
    }

}
