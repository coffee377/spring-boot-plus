package com.voc.restful.core.persist.mongo.impl;

import com.voc.restful.core.entity.IEntity;
import com.voc.restful.core.persist.mongo.IMongoService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/26 17:42
 */
@SuppressWarnings({"unchecked"})
public abstract class BaseMongoService<E extends IEntity, D extends BaseMongoDao>
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

    //    @Override
//    public <ID extends Serializable> ID save(E entity) {
//        IEntity save = mongoDao.save(entity);
//        return (ID) save.getId();
//    }

//
//    @Override
//    public List<E> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public List<E> findAllById(Iterable<ID> ids) {
//        return null;
//    }
//
//    @Override
//    public boolean updateById(ID id, Map<String, Object> updateFieldMap) {
//        return false;
//    }
//
//    @Override
//    public <E1 extends E> boolean update(E1 entity) {
//        return false;
//    }
//
//    @Override
//    public long total(Query condition) {
//        return 0;
//    }
//
//    @Override
//    public List<E> find(Query condition) {
//        return null;
//    }
//
//    @Override
//    public Page<E> findPage(Query condition, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public Optional<E> findOne(Query condition) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<E> findById(Iterable<ID> ids) {
//        return null;
//    }
//
//    @Override
//    public Page<E> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends E> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends E> Iterable<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<E> findById(ID id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(ID id) {
//        return false;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(ID id) {
//
//    }
//
//    @Override
//    public void delete(E entity) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends E> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }

//    @Override
//    public List<Menu> findAllById(Iterable<MenuDao> menuDaos) {
//        return null;
//    }
//
//    @Override
//    public boolean updateById(MenuDao menuDao, Map<String, Object> updateFieldMap) {
//        return false;
//    }
//
//    @Override
//    public List<Menu> findById(Iterable<MenuDao> menuDaos) {
//        return null;
//    }
//
//    @Override
//    public Optional<Menu> findById(MenuDao menuDao) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(MenuDao menuDao) {
//        return false;
//    }
//
//    @Override
//    public void deleteById(MenuDao menuDao) {
//
//    }

}
