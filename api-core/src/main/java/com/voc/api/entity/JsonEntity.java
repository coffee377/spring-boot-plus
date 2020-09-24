package com.voc.api.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voc.api.bean.IBean;
import com.voc.api.utils.SpringUtil;
import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/11/09 15:49
 */
public class JsonEntity implements IBean {

    @SneakyThrows
    @Override
    public String toString() {
        ObjectMapper mapper = SpringUtil.getBean(ObjectMapper.class);
        return mapper.writeValueAsString(this);
    }

}
