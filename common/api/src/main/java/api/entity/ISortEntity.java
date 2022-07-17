package api.entity;

import api.bean.ISort;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 20:22
 */
public interface ISortEntity<ID extends Serializable> extends IEntity<ID>, ISort {
}
