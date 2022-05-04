package com.voc.restful.core.persist.mybatis;

import com.voc.restful.core.persist.entity.BizEntity;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/17 00:10
 */
public interface IBaseService<ID extends Serializable, D extends IBaseDao, BO extends BizEntity> {

    /**
     * 获取 Dao 层接口
     *
     * @return D
     */
    D getDao();

    /**
     * 获取持久化对象类
     *
     * @return Class<BO>
     */
    Class<BO> getPersistEntityClass();

    /**
     * 添加单个实体
     *
     * @param entity 实体对象
     * @return 主键字符串
     */
    boolean save(BO entity);

    /**
     * 批量添加
     *
     * @param entities 实体对象集合
     * @return List<String>
     */
    default boolean saveBatch(Collection<BO> entities) {
        return this.saveBatch(entities, 100);
    }

    /**
     * 批量添加
     *
     * @param entities  实体对象集合
     * @param batchSize 批量大小
     * @return boolean
     */
    boolean saveBatch(Collection<BO> entities, int batchSize);

//
//    /**
//     * 根据 ID 删除记录
//     *
//     * @param id 主键
//     */
//    void deleteById(Object id);
//
//    /**
//     * 批量删除数据
//     *
//     * @param ids 主键集合
//     */
//    void deleteByIds(Iterable<Object> ids);
//
//    /**
//     * 更新实体
//     *
//     * @param entity 实体对象
//     * @return 修改成功后的数据
//     */
//    T update(T entity);
//
//    /**
//     * 根据ID更新指定字段信息
//     *
//     * @param id             更新主键
//     * @param updateFieldMap key:需要更新的属性  value:对应的属性值
//     * @return T
//     */
//    T updateById(Object id, Map<String, Object> updateFieldMap);
//
//    /**
//     * 批量更新实体
//     *
//     * @param entities 实体对象集合
//     * @return 修改成功后的数据
//     */
//    List<T> updateAll(Iterable<T> entities);
//
//    /**
//     * 查询所有数据
//     *
//     * @return List<T>
//     */
//    List<T> findAll();
//
//    /**
//     * 排序查询所有数据
//     *
//     * @param sort Sort
//     * @return List<T>
//     */
//    List<T> findAll(Sort sort);
//
//    /**
//     * 分页查询
//     *
//     * @param pageable 分页对象
//     * @return Page<T>
//     */
//    Page<T> findAll(Pageable pageable);
//
//    /**
//     * 指定查询条件记录数,condition 为空即查询所有
//     *
//     * @param condition Query
//     * @return long
//     */
//    long total(Query condition);
//
//    /**
//     * 获取指定 ID 实体
//     *
//     * @param id 主键ID
//     * @return T
//     * @throws BizException 业务异常
//     */
//    T findById(Object id) throws BizException;
//
//    /**
//     * 查询指定添加记录
//     *
//     * @param condition 查询条件
//     * @return List<T>
//     */
//    List<T> find(Query condition);
//
//    /**
//     * 查询满足指定条件的第一条记录
//     *
//     * @param condition 查询条件
//     * @return Optional<T>
//     */
//    Optional<T> findOne(Query condition);
//
//    /**
//     * 分页查询
//     *
//     * @param condition 查询条件
//     * @param pageable  分页属性
//     * @return Page<T>
//     */
//    Page<T> findPage(Query condition, Pageable pageable);

}
