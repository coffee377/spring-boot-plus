package com.voc.system.dao.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.voc.system.dao.IMongoDao;
import com.voc.system.entity.IEntity;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.BasicMongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.data.util.StreamUtils;
import org.springframework.data.util.Streamable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/07 10:28
 */
public class BaseMongoDao<T extends IEntity, ID> implements IMongoDao<T, ID>, ApplicationContextAware {

    protected MongoOperations mongoOperations;

    protected MongoEntityInformation<T, ID> entityInformation;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.mongoOperations = applicationContext.getBean(MongoOperations.class);
        this.resolveEntityInformation(applicationContext);
    }

    @SuppressWarnings({"unchecked"})
    protected void resolveEntityInformation(ApplicationContext applicationContext) {
        ResolvableType resolvableType = ResolvableType.forClass(IMongoDao.class, this.getClass());
        Class<T> entityClazz = (Class<T>) resolvableType.getGeneric(0).toClass();
        Class<ID> idClazz = (Class<ID>) resolvableType.getGeneric(1).toClass();
        MongoMappingContext mongoMappingContext = applicationContext.getBean(MongoMappingContext.class);
        BasicMongoPersistentEntity<T> persistentEntity = (BasicMongoPersistentEntity<T>) mongoMappingContext.getPersistentEntity(entityClazz);
        assert persistentEntity != null;
        this.entityInformation = new MappingMongoEntityInformation<>(persistentEntity, idClazz);
    }

    @Override
    public <E extends T> E add(E entity) {
        Assert.notNull(entity, "Entity must not be null!");
        return mongoOperations.insert(entity, entityInformation.getCollectionName());
    }

    @Override
    public <E extends T> List<E> add(Iterable<E> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");

        List<E> list = Streamable.of(entities).stream().collect(StreamUtils.toUnmodifiableList());

        if (list.isEmpty()) {
            return list;
        }

        return new ArrayList<>(mongoOperations.insertAll(list));
    }

    @Override
    public void deleteById(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        mongoOperations.remove(getIdQuery(id), entityInformation.getJavaType(), entityInformation.getCollectionName());
    }

    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "The given entity must not be null!");
        DeleteResult deleteResult = mongoOperations.remove(entity, entityInformation.getCollectionName());
        if (entityInformation.isVersioned() && deleteResult.wasAcknowledged() && deleteResult.getDeletedCount() == 0) {
            throw new OptimisticLockingFailureException(String.format(
                    "The entity with id %s with version %s in %s cannot be deleted! Was it modified or deleted in the meantime?",
                    entityInformation.getId(entity), entityInformation.getVersion(entity),
                    entityInformation.getCollectionName()));
        }
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        mongoOperations.remove(new Query(), entityInformation.getCollectionName());
    }

    @Override
    public boolean updateById(ID id, Map<String, Object> updateFieldMap) {
        Query idQuery = getIdQuery(id);
        Update update = new Update();
        updateFieldMap.forEach((key, value) -> {
            if (!entityInformation.getIdAttribute().equals(key) && !ObjectUtils.isEmpty(value)) {
                update.set(key, value);
            }
        });
        UpdateResult updateResult = mongoOperations.updateFirst(idQuery, update,
                entityInformation.getJavaType(), entityInformation.getCollectionName());
        return updateResult.getModifiedCount() > 0;
    }

    @Override
    public <E extends T> boolean update(E entity) {
//        String idAttribute = entityInformation.getIdAttribute();
        Query idQuery = getIdQuery(entity.getId());
        Update update = new Update();
//        Update.update("name",entity);
        UpdateResult updateResult = mongoOperations.updateFirst(idQuery, update, entityInformation.getJavaType(), entityInformation.getCollectionName());
        return false;
    }

    @Override
    public long total(Query condition) {
        return mongoOperations.count(ensureQuery(condition), entityInformation.getCollectionName());
    }

    @Override
    public List<T> find(Query condition) {
        return mongoOperations.find(ensureQuery(condition), entityInformation.getJavaType(), entityInformation.getCollectionName());
    }

    @Override
    public Page<T> findPage(Query condition, Pageable pageable) {
        Assert.notNull(pageable, "Pageable must not be null!");
        Query query = ensureQuery(condition).with(pageable);
        List<T> list = find(query);
        long total = total(query);
        return new PageImpl<>(list, pageable, total);
    }

    @Override
    public Optional<T> findOne(Query condition) {
        T one = mongoOperations.findOne(ensureQuery(condition), entityInformation.getJavaType(), entityInformation.getCollectionName());
        return Optional.ofNullable(one);
    }

    @Override
    public Optional<T> findById(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        return Optional.ofNullable(
                mongoOperations.findById(id, entityInformation.getJavaType(), entityInformation.getCollectionName()));
    }

    @Override
    public List<T> findById(Iterable<ID> ids) {
        Assert.notNull(ids, "The given Ids of entities not be null!");
        List<ID> idList = Streamable.of(ids).stream().collect(StreamUtils.toUnmodifiableList());
        Query query = Query.query(where(entityInformation.getIdAttribute()).in(idList));
        return find(query);
    }

    @Override
    public List<T> findAll() {
        return find(new Query());
    }

    @Override
    public List<T> findAll(Sort sort) {
        return find(new Query().with(sort));
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        Query query = new Query().with(pageable);
        List<T> list = find(query);
        long total = total(query);
        return new PageImpl<>(list, pageable, total);
    }

    private Query ensureQuery(Query query) {
        return Optional.ofNullable(query).orElse(new Query());
    }

    private Query getIdQuery(Object id) {
        return new Query(getIdCriteria(id));
    }

    private Criteria getIdCriteria(Object id) {
        return where(entityInformation.getIdAttribute()).is(id);
    }

}

