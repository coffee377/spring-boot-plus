package api.bean;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 19:58
 */
public interface ITree<ID extends Serializable> {

    /**
     * 获取 id
     *
     * @return ID
     */
    ID getParentId();

    /**
     * 设置 id
     *
     * @param id ID
     */
    void setParentId(ID id);

}
