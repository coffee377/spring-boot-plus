package com.voc.system.entity.mapstruct;

import com.voc.system.entity.bo.UserBO;
import com.voc.system.entity.po.UserPO;
import com.voc.system.entity.vo.UserVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-13T23:12:37+0800",
    comments = "version: 1.5.0.RC1, compiler: javac, environment: Java 11.0.10 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserPO convertBO2PO(UserBO userBO) {
        if ( userBO == null ) {
            return null;
        }

        UserPO userPO = new UserPO();

        userPO.setJobNumber( userBO.getJobNumber() );
        userPO.setMobile( userBO.getMobile() );
        userPO.setEmail( userBO.getEmail() );
        userPO.setRealName( userBO.getRealName() );
        userPO.setAvatar( userBO.getAvatar() );

        return userPO;
    }

    @Override
    public UserVO convertPO2VO(UserPO userPO) {
        if ( userPO == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        userVO.setUid( userPO.getId() );
        userVO.setRealName( userPO.getRealName() );
        userVO.setMobile( userPO.getMobile() );
        userVO.setEmail( userPO.getEmail() );
        userVO.setAvatar( userPO.getAvatar() );

        return userVO;
    }
}
