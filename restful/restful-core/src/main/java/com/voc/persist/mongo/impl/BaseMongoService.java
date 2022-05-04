package com.voc.persist.mongo.impl;

import com.voc.persist.mongo.IMongoService;
import com.voc.restful.core.entity.IJsonEntity;
import com.voc.restful.core.response.BizException;
import com.voc.restful.core.response.impl.BaseBizStatus;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/26 17:42
 */
@SuppressWarnings({"unchecked"})
public abstract class BaseMongoService<E extends IJsonEntity, D extends BaseMongoDao>
        implements IMongoService<E, D>, ApplicationContextAware {

    private D mongoDao;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ResolvableType resolvableType = ResolvableType.forClass(IMongoService.class, this.getClass());
        Class<D> daoClazz = (Class<D>) resolvableType.getGeneric(1).toClass();
        this.mongoDao = applicationContext.getBean(daoClazz);
    }

    protected D getDao() {
        return mongoDao;
    }

    @Override
    public String save(E entity) {
        return mongoDao.save(entity).getId().toString();
    }

    @Override
    public List<String> saveAll(Iterable<E> entities) {
        List<E> list = mongoDao.saveAll(entities);
        return list.stream().map(e -> e.getId().toString()).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Object id) {
        mongoDao.deleteById(id);
    }

    @Override
    public void deleteByIds(Iterable<Object> ids) {
        ids.forEach(this::deleteById);
    }

    @Override
    public E update(E entity) {
        return (E) mongoDao.update(entity);
    }

    @Override
    public E updateById(Object id, Map<String, Object> updateFieldMap) {
        return (E) mongoDao.updateById(id, updateFieldMap);
    }

    @Override
    public List<E> updateAll(Iterable<E> entities) {
        LinkedList<E> list = new LinkedList<>();
        entities.forEach(entity -> {
            E update = this.update(entity);
            list.add(update);
        });
        return new ArrayList<>(list);
    }

    @Override
    public List<E> findAll() {
        return mongoDao.findAll();
    }

    @Override
    public List<E> findAll(Sort sort) {
        return mongoDao.findAll(sort);
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return mongoDao.findAll(pageable);
    }

    @Override
    public long total(Query condition) {
        return mongoDao.total(condition);
    }

    @Override
    public E findById(Object id) {
        Optional<E> optional = mongoDao.findById(id);
        return optional.orElseThrow(() -> new BizException(BaseBizStatus.RECORD_NOT_EXISTS));
    }

    @Override
    public List<E> find(Query condition) {
        return mongoDao.find(condition);
    }

    @Override
    public Optional<E> findOne(Query condition) {
        return mongoDao.findOne(condition);
    }

    @Override
    public Page<E> findPage(Query condition, Pageable pageable) {
        return mongoDao.findPage(condition, pageable);
    }
}
