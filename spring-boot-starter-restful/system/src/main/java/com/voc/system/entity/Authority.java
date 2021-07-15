package com.voc.system.entity;

import com.voc.restful.core.entity.BaseEntity;
import com.voc.system.constant.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpMethod;

/**
 * 接口权限
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/14 15:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = TableName.AUTHORITY)
public class Authority extends BaseEntity<String> {

    /**
     * 全局唯一编码
     */
    String code;

    /**
     * 接口名称
     */
    String name;

    /**
     * 接口路径
     */
    String path;

    /**
     * 接口请求方法
     */
    HttpMethod method;
}
