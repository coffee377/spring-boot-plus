package com.voc.system.entity.vo.menu;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 18:07
 */
public interface IAuthority {
    /**
     * 获取操作权限
     *
     * @return
     */
    List<Object> getOperation();

    /**
     * 获取数据权限
     *
     * @return
     */
    List<Object> getData();
}
