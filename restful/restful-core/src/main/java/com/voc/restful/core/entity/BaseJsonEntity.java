package com.voc.restful.core.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/11/09 15:49
 */
public abstract class BaseJsonEntity<ID extends Serializable> extends BaseEntity<ID> implements IJsonEntity<ID> {

}
