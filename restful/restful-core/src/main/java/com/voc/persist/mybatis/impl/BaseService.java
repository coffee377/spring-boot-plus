package com.voc.persist.mybatis.impl;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.voc.persist.PersistEntity;
import com.voc.persist.mybatis.IBaseDao;
import com.voc.persist.mybatis.IBaseService;
import com.voc.restful.core.persist.entity.BizEntity;
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
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/21 18:17
 */
@SuppressWarnings({"unchecked"})
public abstract class BaseService<ID extends Serializable, Dao extends IBaseDao, P extends PersistEntity,
        B extends BizEntity> implements IBaseService<ID, Dao, P, B>, ApplicationContextAware {
    protected Log log = LogFactory.getLog(this.getClass());
    protected ApplicationContext context;

    protected Dao baseDao;

    protected Class<Dao> mapperClass;
    protected Class<P> persistEntityClass;
    protected Class<B> bizEntityClass;

    public BaseService() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        ResolvableType resolvableType = ResolvableType.forClass(IBaseService.class, this.getClass());

        mapperClass = (Class<Dao>) resolvableType.getGeneric(1).toClass();
        baseDao = context.getBean(mapperClass);

        persistEntityClass = (Class<P>) resolvableType.getGeneric(2).toClass();
        bizEntityClass = (Class<B>) resolvableType.getGeneric(3).toClass();
    }

    @Override
    public Dao getDao() {
        return baseDao;
    }

    @Override
    public Class<P> getPersistEntityClass() {
        return persistEntityClass;
    }

    @Override
    public Class<B> getBizEntityClass() {
        return bizEntityClass;
    }

    @Override
    public boolean save(B entity) {
        P persist = this.convert(entity);
        int result = this.getDao().insert(persist);
        return SqlHelper.retBool(result);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveBatch(Collection<B> entities, int batchSize) {
        List<P> collects = entities.stream().map(this::convert).collect(Collectors.toList());
        String sqlStatement = this.getSqlStatement(SqlMethod.INSERT_ONE);
        return this.executeBatch(collects, batchSize, (sqlSession, entity) -> {
            sqlSession.insert(sqlStatement, entity);
        });
    }

    @Override
    public boolean deleteById(ID id) {
        return SqlHelper.retBool(this.getDao().deleteById(id));
    }

    @Override
    public boolean deleteByIds(Collection<? extends Serializable> ids) {
        return !CollectionUtils.isEmpty(ids) && SqlHelper.retBool(this.getDao().deleteBatchIds(ids));
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

    protected  boolean executeBatch(Collection<P> list, int batchSize, BiConsumer<SqlSession, P> consumer) {
        return SqlHelper.executeBatch(this.persistEntityClass, this.log, list, batchSize, consumer);
    }

    protected  boolean executeBatch(Collection<P> list, BiConsumer<SqlSession, P> consumer) {
        return this.executeBatch(list, 1000, consumer);
    }


}
