package com.voc.persist.mybatis;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.voc.persist.PersistEntity;
import com.voc.restful.core.persist.entity.BizEntity;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/17 00:10
 */
@SuppressWarnings({"unchecked"})
public interface IBaseService<ID extends Serializable, Dao extends IBaseDao, PO extends PersistEntity, BO extends BizEntity> {

    /**
     * 获取 Dao 层接口
     *
     * @return D
     */
    Dao getDao();

    /**
     * 获取持久化对象类
     *
     * @return Class<PO>
     */
    Class<PO> getPersistEntityClass();

    /**
     * 获取业务对象类
     *
     * @return Class<BO>
     */
    Class<BO> getBizEntityClass();

    /**
     * 业务对象转持久化对象
     *
     * @param bo 业务对象
     * @return 持久化对象
     */
    PO convert(BO bo);

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

    /**
     * 根据 ID 删除记录
     *
     * @param id 主键
     * @return boolean
     */
    boolean deleteById(ID id);

    /**
     * 批量删除数据
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean deleteByIds(Collection<? extends Serializable> ids);

//    default boolean deleteByMap(Map<String, Object> columnMap) {
//        Assert.notEmpty(columnMap, "error: columnMap must not be empty");
//        return SqlHelper.retBool(this.getDao().deleteByMap(columnMap));
//    }

    /**
     * 条件删除
     *
     * @param queryWrapper Wrapper<PO>
     * @return boolean
     */
    default boolean delete(Wrapper<PO> queryWrapper) {
        return SqlHelper.retBool(this.getDao().delete(queryWrapper));
    }

    /**
     * 根据 id 更新实体
     * @param id 主键 id
     * @param entity 更新信息
     * @return boolean
     */
    default boolean updateById(ID id, BO entity) {
        PO persist = this.convert(entity);
        persist.setId(id);
        return SqlHelper.retBool(this.getDao().updateById(persist));
    }
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
