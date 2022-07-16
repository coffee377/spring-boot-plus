package com.voc.oss;

import java.io.InputStream;

/**
 * 对象存储服务
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/19 17:11
 */
public interface ObjectStorageService {
    /**
     * 存储对象
     *
     * @param bucketName  存储空间名称
     * @param objectName  存储对象名称（包含完整路径名称）
     * @param inputStream 对象的输入流
     * @param contentType 参考http 的 MimeType 值
     * @throws Exception the exception
     */
    void putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception;

    /**
     * 删除对象
     *
     * @param bucketName 存储空间名称
     * @param objectName 存储对象名称（包含完整路径名称）
     */
    void removeObject(String bucketName, String objectName) throws RuntimeException;

    /**
     * 获取对象
     *
     * @param bucketName 存储空间名称
     * @param objectName 存储对象名称（包含完整路径名称）
     * @return the object
     */
    InputStream getObject(String bucketName, String objectName) throws RuntimeException;

    /**
     * 获取对象的URL地址
     *
     * @param bucketName 存储空间名称
     * @param objectName 存储对象名称（包含完整路径名称）
     * @return the object url
     */
    String getObjectUrl(String bucketName, String objectName) throws RuntimeException;
}
