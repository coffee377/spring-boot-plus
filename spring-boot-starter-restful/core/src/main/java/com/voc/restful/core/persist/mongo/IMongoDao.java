package com.voc.restful.core.persist.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
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
public interface IMongoDao<T, ID> extends PagingAndSortingRepository<T, ID> {

    /**
     * 查找所有
     *
     * @return list<t>
     */
    @Override
    List<T> findAll();

    /**
     * 排序查找
     *
     * @param sort 排序
     * @return List<T>
     */
    @Override
    List<T> findAll(Sort sort);

    /**
     * 根据 ID 集合查询数据
     *
     * @param ids ID 集合
     * @return List<T>
     */
    @Override
    List<T> findAllById(Iterable<ID> ids);

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
     * 根据 ID 集合查询
     *
     * @param ids Iterable<ID>
     * @return List<T>
     */
    List<T> findById(Iterable<ID> ids);

}
