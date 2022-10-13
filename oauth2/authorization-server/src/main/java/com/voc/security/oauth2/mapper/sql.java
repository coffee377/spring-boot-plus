package com.voc.security.oauth2.mapper;

import org.apache.ibatis.jdbc.SQL;


/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/11 21:14
 */
public class sql {

    private String insert() {
        return new SQL() {{
            INSERT_INTO("oauth2_client");
            VALUES("ID, FIRST_NAME", "#{id}, #{firstName}");
            VALUES("LAST_NAME", "#{lastName}");
        }}.toString();
    }
}
