package com.voc.restful.core.persist.mongo;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/26 17:42
 */
public interface IMongoService<T, D> {

    /**
     * 保存实体
     *
     * @param entity 实体对象
     * @return 主键字符串
     */
    String save(T entity);

//    boolean insert(T entity);
//
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
