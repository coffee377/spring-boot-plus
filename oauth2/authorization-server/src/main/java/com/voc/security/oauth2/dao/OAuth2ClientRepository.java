package com.voc.security.oauth2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.voc.security.oauth2.entity.po.OAuth2Client;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/11 17:14
 */
public interface OAuth2ClientRepository extends BaseMapper<OAuth2Client> {

//    /**
//     * 添加客户端
//     *
//     * @return OAuth2Client
//     */
//    @Insert("")
//    String insert1(@Param("client") OAuth2Client client);
//
//    @Update("")
//    OAuth2Client updateById(@Param("client") OAuth2Client client);
//
//    //    @Delete.List({
////            @Delete("DELETE from oauth2_client WHERE id = #{id}"),
////            @Delete("DELETE from oauth2_client WHERE id = #{id}")
////    })
//    boolean deleteById(@Param("id") String id);
//
//
//    /**
//     * 根据 id 查询 客户端
//     *
//     * @param id 主键标识
//     * @return OAuth2Client
//     */
//    @Select("SELECT * from oauth2_client WHERE id = #{id}")
//    OAuth2Client findById(@Param("id") String id);
//
//    OAuth2Client findByClientId(@Param("id") String id);

}
