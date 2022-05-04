package com.voc.restful.core.persist.mybatis.impl;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.voc.restful.core.persist.entity.BizEntity;
import com.voc.restful.core.persist.entity.PersistEntity;
import com.voc.restful.core.persist.entity.QueryEntity;
import com.voc.restful.core.persist.mybatis.IBaseDao;
import com.voc.restful.core.persist.mybatis.IBaseService;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.BiConsumer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/21 18:17
 */
public class BaseService<ID extends Serializable, D extends IBaseDao, P extends PersistEntity, B extends BizEntity,
        Q extends QueryEntity> implements IBaseService<ID, D, B>, ApplicationContextAware {
    protected Log log = LogFactory.getLog(this.getClass());

    protected D baseDao;
    protected Class<P> persistEntityClass;
    protected Class<D> mapperClass;
    protected ApplicationContext context;

    public BaseService() {
        ResolvableType resolvableType = ResolvableType.forClass(IBaseService.class, this.getClass());
        baseDao = context.getBean(mapperClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public D getDao() {
        return baseDao;
    }

    @Override
    public Class<B> getPersistEntityClass() {
        return null;
    }


    @Override
    public boolean save(B entity) {
        int result = this.getDao().insert(entity);
        return SqlHelper.retBool(result);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveBatch(Collection<B> entities, int batchSize) {
        String sqlStatement = this.getSqlStatement(SqlMethod.INSERT_ONE);
        return this.executeBatch(entities, batchSize, (sqlSession, entity) -> {
            sqlSession.insert(sqlStatement, entity);
        });
    }

    /**
     * 获取 SQL 语句
     *
     * @param sqlMethod SqlMethod
     * @return String
     */
    protected String getSqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.getSqlStatement(this.mapperClass, sqlMethod);
    }

    protected <E> boolean executeBatch(Collection<E> list, int batchSize, BiConsumer<SqlSession, E> consumer) {
        return SqlHelper.executeBatch(this.persistEntityClass, this.log, list, batchSize, consumer);
    }

    protected <E> boolean executeBatch(Collection<E> list, BiConsumer<SqlSession, E> consumer) {
        return this.executeBatch(list, 1000, consumer);
    }

    //    @Override
//    public ID insert(B bizEntity) {
////        return this.getDao().insert(converBO2PO(bizEntity));
//        return null;
//    }
//
//    public PersistEntity converBO2PO(BizEntity bizEntity) {
//        return null;
//    }

}
