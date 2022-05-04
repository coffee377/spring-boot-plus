package com.voc.system.entity.bo;

import com.voc.restful.core.persist.entity.BizEntity;
import lombok.Data;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/21 14:26
 */
@Data
public class UserBO implements BizEntity {

    private String username;
    private String password;
    private String realName;
    private String jobNumber;
    private String mobile;
    private String email;
    private String avatar;
}
