package com.voc.restful.core.persist.mongo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/26 17:42
 */
public interface IMongoService<T, D> {

    /**
     * 添加单个实体
     *
     * @param entity 实体对象
     * @return 主键字符串
     */
    String save(T entity);

    /**
     * 批量添加
     *
     * @param entities 实体对象集合
     * @return List<String>
     */
    List<Serializable> saveAll(Iterable<T> entities);

    /**
     * 根据 ID 删除记录
     *
     * @param id 主键
     */
    void deleteById(Object id);

    /**
     * 批量删除数据
     *
     * @param ids 主键集合
     */
    void deleteByIds(Iterable<Object> ids);

    /**
     * 更新实体
     *
     * @param entity 实体对象
     * @return 修改成功后的数据
     */
    T update(T entity);

    /**
     * 根据ID更新指定字段信息
     *
     * @param id             更新主键
     * @param updateFieldMap key:需要更新的属性  value:对应的属性值
     * @return T
     */
    T updateById(Object id, Map<String, Object> updateFieldMap);

    /**
     * 批量更新实体
     *
     * @param entities 实体对象集合
     * @return 修改成功后的数据
     */
    List<T> updateAll(Iterable<T> entities);

    /**
     * 查询所有数据
     *
     * @return List<T>
     */
    List<T> findAll();

//    boolean insertBatch(List<T> entities);
//
//    <ID extends Serializable> boolean deleteById(ID id);
//
//    boolean deleteBatch(Collection<? extends Serializable> ids);
//
//    boolean deleteBatchIds(String ids);
//
//    boolean update(T entity);
//
//    boolean updateBatch(List<T> entities);
//
//    boolean exists(Serializable id);
//
//    T selectOne(Serializable id);
//
//    T selectOne(Map<String, Object> params);
//
//    List<T> selectList(Map<String, Object> params);
//
//    PageResult<T> selectPage(Integer startPageNum, Integer limit, Map<String, Object> params);
}
