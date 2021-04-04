package com.voc.system.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/07 10:25
 */
@NoRepositoryBean
public interface IMongoDao<T, ID> {

    /**
     * 新增数据
     *
     * @param entity E
     * @param <E>    E extends T
     * @return E
     */
    <E extends T> E add(E entity);

    /**
     * 新增数据
     *
     * @param entities Iterable<E>
     * @param <E>      E extends T
     * @return List<E>
     */
    <E extends T> List<E> add(Iterable<E> entities);

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     */
    void deleteById(ID id);

    /**
     * Deletes a given entity.
     *
     * @param entity must not be {@literal null}.
     */
    void delete(T entity);

    /**
     * Deletes the given entities.
     *
     * @param entities must not be {@literal null}. Must not contain {@literal null} elements.
     */
    void deleteAll(Iterable<? extends T> entities);

    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll();

    /**
     * 根据id修改
     *
     * @param id             更新主键
     * @param updateFieldMap key:需要更新的属性  value:对应的属性值
     * @return 是否更新成功
     */
    boolean updateById(ID id, Map<String, Object> updateFieldMap);

    /**
     * 更新数据
     *
     * @param entity E
     * @param <E>    E extends T
     * @return E
     */
    <E extends T> boolean update(E entity);

    /**
     * 数据总记录数
     *
     * @param condition 查询条件
     * @return the number of entities.
     */
    long total(@Nullable Query condition);

    /**
     * 查询数据
     *
     * @param condition condition 查询条件
     * @return List<T>
     */
    List<T> find(@Nullable Query condition);

    /**
     * 查询分页数据
     *
     * @param condition 查询条件
     * @param pageable  分页信息
     * @return Page<T>
     */
    Page<T> findPage(@Nullable Query condition, Pageable pageable);

    /**
     * 查询一条数据
     *
     * @param condition 查询条件
     * @return Optional<T>
     */
    Optional<T> findOne(@Nullable Query condition);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return Optional<T>
     */
    Optional<T> findById(ID id);

    /**
     * 根据 ID 集合查询
     *
     * @param ids Iterable<ID>
     * @return List<T>
     */
    List<T> findById(Iterable<ID> ids);

    /**
     * 查询所有数据
     *
     * @return List<T>
     */
    List<T> findAll();

    /**
     * 查询所有数据
     *
     * @param sort 排序信息
     * @return List<T>
     */
    List<T> findAll(Sort sort);

    /**
     * 查询所有数据
     *
     * @param pageable 分页信息
     * @return List<T>
     */
    Page<T> findAll(Pageable pageable);


//    /**
//     * 根据传入的对象 修改
//     *
//     * @param id     主键
//     * @param entity 实体
//     */
//    void update(ID id, T entity);
//
//    /**
//     * 根据id修改
//     *
//     * @param id             更新主键
//     * @param updateFieldMap key:需要更新的属性  value:对应的属性值
//     */
//    void update(ID id, Map<String, Object> updateFieldMap);
}
