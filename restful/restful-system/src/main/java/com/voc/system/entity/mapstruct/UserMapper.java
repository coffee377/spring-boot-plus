package com.voc.system.entity.mapstruct;

import com.voc.system.entity.bo.UserBO;
import com.voc.system.entity.po.UserPO;
import com.voc.system.entity.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/22 16:50
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserPO convertBO2PO(UserBO userBO);

    @Mapping(target = "uid",source = "id")
    UserVO convertPO2VO(UserPO userPO);
}
