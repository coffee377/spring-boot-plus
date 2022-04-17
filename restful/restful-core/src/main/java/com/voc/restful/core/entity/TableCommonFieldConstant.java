package com.voc.restful.core.entity;

/**
 * 数据库表公共字段常量
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/04/15 13:33
 */
public interface TableCommonFieldConstant {

	/**
	 * 主键
	 */
	String ID = "id";

	/**
	 * 创建人
	 */
	String CREATED_BY = "createdBy";

	/**
	 * 创建时间
	 */
	String CREATED_AT = "createdAt";

	/**
	 * 修改人
	 */
	String UPDATED_BY = "updatedBy";

	/**
	 * 修改时间
	 */
	String UPDATED_AT = "updatedAt";

	/**
	 * 数据标识
	 */
	String DATA_FLAG = "flag";

	/**
	 * 使用状态
	 */
	String USING_STATUS = "status";
}
