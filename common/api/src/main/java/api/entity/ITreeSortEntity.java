package api.entity;

import api.bean.ITreeSort;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 20:22
 */
public interface ITreeSortEntity<ID extends Serializable> extends IEntity<ID>, ITreeSort<ID> {


}
