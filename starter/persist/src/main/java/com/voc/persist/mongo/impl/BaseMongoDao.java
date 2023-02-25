package com.voc.persist.mongo.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.voc.persist.mongo.IMongoDao;
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
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapping.BasicMongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.data.util.StreamUtils;
import org.springframework.data.util.Streamable;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/07 10:28
 */
public abstract class BaseMongoDao<E, ID> implements IMongoDao<E, ID>, ApplicationContextAware {

    protected MongoOperations mongoOperations;

    protected MongoEntityInformation<E, ID> entityInformation;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.mongoOperations = applicationContext.getBean(MongoOperations.class);
        this.resolveEntityInformation(applicationContext);
    }

    @SuppressWarnings({"unchecked"})
    protected void resolveEntityInformation(ApplicationContext applicationContext) {
        ResolvableType resolvableType = ResolvableType.forClass(IMongoDao.class, this.getClass());
        Class<E> entityClazz = (Class<E>) resolvableType.getGeneric(0).toClass();
        Class<ID> idClazz = (Class<ID>) resolvableType.getGeneric(1).toClass();
        MongoMappingContext mongoMappingContext = applicationContext.getBean(MongoMappingContext.class);
        BasicMongoPersistentEntity<E> persistentEntity = (BasicMongoPersistentEntity<E>) mongoMappingContext.getPersistentEntity(entityClazz);
        assert persistentEntity != null;
        this.entityInformation = new MappingMongoEntityInformation<>(persistentEntity, idClazz);
    }

    @Override
    public <S extends E> S save(S entity) {
        Assert.notNull(entity, "Entity must not be null!");
        if (entityInformation.isNew(entity)) {
            return mongoOperations.insert(entity, entityInformation.getCollectionName());
        }
        return mongoOperations.save(entity, entityInformation.getCollectionName());
    }

    @Override
    public <S extends E> List<S> saveAll(Iterable<S> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        Streamable<S> source = Streamable.of(entities);
        boolean allNew = source.stream().allMatch(entityInformation::isNew);
        if (allNew) {
            List<S> result = source.stream().collect(Collectors.toList());
            return new ArrayList<>(mongoOperations.insert(result, entityInformation.getCollectionName()));
        }
        return source.stream().map(this::save).collect(Collectors.toList());
    }

    @Override
    public Optional<E> findById(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        return Optional.ofNullable(
                mongoOperations.findById(id, entityInformation.getJavaType(), entityInformation.getCollectionName()));
    }

    @Override
    public boolean existsById(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        return mongoOperations.exists(getIdQuery(id), entityInformation.getJavaType(),
                entityInformation.getCollectionName());
    }

    @Override
    public long count() {
        return mongoOperations.count(new Query(), entityInformation.getCollectionName());
    }

    @Override
    public void deleteById(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        DeleteResult deleteResult = mongoOperations.remove(getIdQuery(id), entityInformation.getJavaType(), entityInformation.getCollectionName());
        if (entityInformation.isVersioned() && deleteResult.wasAcknowledged() && deleteResult.getDeletedCount() == 0) {
            E entity = this.findOne(getIdQuery(id)).orElse(null);
            assert entity != null;
            throw new OptimisticLockingFailureException(String.format(
                    "The entity with id %s with version %s in %s cannot be deleted! Was it modified or deleted in the meantime?",
                    id, entityInformation.getVersion(entity),
                    entityInformation.getCollectionName()));
        }
    }

    @Override
    public void delete(E entity) {
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
    public void deleteAllById(Iterable<? extends ID> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends E> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        mongoOperations.remove(new Query(), entityInformation.getCollectionName());
    }

    @Override
    public List<E> findAll() {
        return findAll(new Query());
    }

    @Override
    public List<E> findAllById(Iterable<ID> ids) {
        Assert.notNull(ids, "The given Ids of entities not be null!");
        return findAll(new Query(new Criteria(entityInformation.getIdAttribute())
                .in(Streamable.of(ids).stream().collect(StreamUtils.toUnmodifiableList()))));
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        Assert.notNull(pageable, "Pageable must not be null!");
        long count = count();
        List<E> list = findAll(new Query().with(pageable));
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public List<E> findAll(Sort sort) {
        Assert.notNull(sort, "Sort must not be null!");
        return findAll(new Query().with(sort));
    }

    @Override
    public E updateById(ID id, Map<String, Object> updateFieldMap) {
        return this.innerUpdate(id, updateFieldMap);
    }

    @Override
    public E update(E entity) {
        // TODO: 2023/2/25 20:52
//        Assert.notNull(entity.getId(), "The Entity id must not be null!");
//        Document document = Document.parse(entity.toJson());
//        return this.innerUpdate(entity.getId(), document);
        return null;
    }

    protected E innerUpdate(Object id, Map<String, Object> updateFieldMap) {
        Query idQuery = getIdQuery(id);
        Update update = new Update();
        updateFieldMap.entrySet().stream()
                .filter(entry -> !entityInformation.getIdAttribute().equals(entry.getKey()) && !ObjectUtils.isEmpty(entry.getValue()))
                .forEach(entry -> update.set(entry.getKey(), entry.getValue()));
        UpdateResult updateResult = mongoOperations.updateFirst(idQuery, update, entityInformation.getJavaType(), entityInformation.getCollectionName());
        if (updateResult.getModifiedCount() > 0) {
            Optional<E> one = this.findOne(idQuery);
            return one.orElse(null);
        }
        return null;
    }

    @Override
    public long total(Query condition) {
        return mongoOperations.count(ensureQuery(condition), entityInformation.getCollectionName());
    }

    @Override
    public List<E> find(Query condition) {
        return mongoOperations.find(ensureQuery(condition), entityInformation.getJavaType(), entityInformation.getCollectionName());
    }

    @Override
    public Page<E> findPage(Query condition, Pageable pageable) {
        Assert.notNull(pageable, "Pageable must not be null!");
        Query query = ensureQuery(condition).with(pageable);
        List<E> list = find(query);
        long total = total(query);
        return new PageImpl<>(list, pageable, total);
    }

    @Override
    public Optional<E> findOne(Query condition) {
        E one = mongoOperations.findOne(ensureQuery(condition), entityInformation.getJavaType(), entityInformation.getCollectionName());
        return Optional.ofNullable(one);
    }

    @Override
    public List<E> findById(Iterable<ID> ids) {
        Assert.notNull(ids, "The given Ids of entities not be null!");
        List<ID> idList = Streamable.of(ids).stream().collect(StreamUtils.toUnmodifiableList());
        Query query = Query.query(where(entityInformation.getIdAttribute()).in(idList));
        return find(query);
    }

    @Override
    public <O> List<O> findByAggregation(Aggregation aggregation, Class<O> outputType) {
//        mongoOperations.
        AggregationResults<O> aggregate = mongoOperations.aggregate(aggregation, entityInformation.getJavaType(), outputType);
        return aggregate.getMappedResults();
    }


    private Query getIdQuery(Object id) {
        return new Query(getIdCriteria(id));
    }

    private Criteria getIdCriteria(Object id) {
        return where(entityInformation.getIdAttribute()).is(id);
    }

    private Query ensureQuery(Query query) {
        return Optional.ofNullable(query).orElse(new Query());
    }

    private List<E> findAll(@Nullable Query query) {
        if (query == null) {
            return Collections.emptyList();
        }
        return mongoOperations.find(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
    }
}

