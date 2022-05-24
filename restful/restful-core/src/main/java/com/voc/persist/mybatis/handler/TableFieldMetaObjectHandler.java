package com.voc.persist.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.voc.persist.IAccountInfo;
import com.voc.common.enums.DataFlag;
import com.voc.common.enums.UsingStatus;
import com.voc.restful.core.entity.TableCommonFieldConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.io.Serializable;
import java.time.Instant;

/**
 * 公共字段自动填充
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/04/15 13:28
 */
@Slf4j
public class TableFieldMetaObjectHandler implements MetaObjectHandler, TableCommonFieldConstant {

    /**
     * 注入当前登录用户账户信息
     */
    private final IAccountInfo account;

    public TableFieldMetaObjectHandler(IAccountInfo account) {
        this.account = account;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        /* 元数据中创建人为空时 */
        Object fieldValByName = getFieldValByName(CREATED_BY, metaObject);
        if (fieldValByName == null) {
            Serializable uid = account.getUserId();
            setFieldValByName(CREATED_BY, uid, metaObject);
//            setFieldValByName(UPDATED_BY, uid, metaObject);
            if (log.isDebugEnabled()) {
                log.debug("公共字段插入增强处理");
                log.debug("创建人：{}", uid);
            }
        }

        /* 元数据中创建时间为空时 */
        fieldValByName = getFieldValByName(CREATED_AT, metaObject);
        if (fieldValByName == null) {
            Instant insertTime = Instant.now();
            setFieldValByName(CREATED_AT, insertTime, metaObject);
//            setFieldValByName(UPDATED_AT, insertTime, metaObject);
            if (log.isDebugEnabled()) {
                log.debug("创建时间：{}", insertTime);
            }

        }

        /* 元数据中数据状态 新增 */
        fieldValByName = getFieldValByName(DATA_FLAG, metaObject);
        if (fieldValByName == null) {
            setFieldValByName(DATA_FLAG, DataFlag.ADD, metaObject);
        }

        /* 数据使用状态 正常 */
        fieldValByName = getFieldValByName(USING_STATUS, metaObject);
        if (fieldValByName == null) {
            setFieldValByName(USING_STATUS, UsingStatus.NORMAL, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("进入元数据修改增强处理");
        Object fieldValByName = getFieldValByName(UPDATED_BY, metaObject);

        /* 元数据中修改人信息与当前用户信息不一致时 */
        Serializable uid = account.getUserId();
        if (!uid.equals(fieldValByName)) {
            setFieldValByName(UPDATED_BY, uid, metaObject);
            if (log.isDebugEnabled()) {
                log.debug("修改人：{}", uid);
            }
        }

        Instant updateTime = Instant.now();
        /* 元数据中修改者时间与当前时间不一致时 */
        fieldValByName = getFieldValByName(UPDATED_AT, metaObject);
        if (!updateTime.equals(fieldValByName)) {
            setFieldValByName(UPDATED_AT, updateTime, metaObject);
            if (log.isDebugEnabled()) {
                log.debug("修改时间：{}", updateTime);
            }
        }

        /* 元数据中数据状态 修改 */
        setFieldValByName(DATA_FLAG, DataFlag.MODIFY, metaObject);

        /* 数据使用状态 正常 */
        fieldValByName = getFieldValByName(USING_STATUS, metaObject);
        if (fieldValByName == null) {
            setFieldValByName(USING_STATUS, UsingStatus.NORMAL, metaObject);
        }

    }

}
