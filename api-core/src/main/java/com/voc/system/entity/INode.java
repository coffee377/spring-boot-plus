package com.voc.system.entity;

import java.util.List;

/**
 * 树形结构
 * Created with IntelliJ IDEA.
 *
 * @param <T>  节点类型
 * @param <PK> 主键类型
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/11/12 13:49
 */
public interface INode<T, PK> {

	/**
	 * 根据主键获取子节点
	 *
	 * @param parentId 节点ID
	 * @return 子节点集合
	 */
	List<T> getChildren(PK parentId);

	/**
	 * 根据id获取节点
	 *
	 * @param id 节点ID
	 * @return 节点
	 */
	T getNode(PK id);

}
