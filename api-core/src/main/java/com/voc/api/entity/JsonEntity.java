package com.voc.api.entity;

import com.voc.api.bean.IBean;
import com.voc.api.autoconfigure.json.IJson;
import com.voc.api.utils.SpringUtil;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/11/09 15:49
 */
public class JsonEntity implements IBean {

    @Override
    public String toString() {
        IJson json = SpringUtil.getBean(IJson.class);
        try {
            return json.serializer(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.toString();
    }

}
