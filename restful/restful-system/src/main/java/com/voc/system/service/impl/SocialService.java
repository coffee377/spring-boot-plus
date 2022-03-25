package com.voc.system.service.impl;

import com.voc.restful.core.persist.mongo.impl.BaseMongoService;
import com.voc.restful.core.response.BizException;
import com.voc.restful.core.response.impl.BaseBizStatus;
import com.voc.system.dao.impl.SocialDao;
import com.voc.system.entity.Social;
import com.voc.system.service.ISocialService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/08 14:44
 */
@Service
public class SocialService extends BaseMongoService<Social, SocialDao> implements ISocialService {

    @Override
    public String bindUser(Social social) {
        List<Criteria> criteriaList = new LinkedList<>();
        criteriaList.add(Criteria.where("uid").is(social.getUid()));
        criteriaList.add(Criteria.where("type").is(social.getType()));

        if (StringUtils.hasText(social.getUnionid())) {
            criteriaList.add(Criteria.where("unionid").is(social.getUnionid()));
        }
        if (StringUtils.hasText(social.getOpenid())) {
            criteriaList.add(Criteria.where("openid").is(social.getOpenid()));
        }

        Criteria criteria = new Criteria().orOperator(criteriaList.toArray(new Criteria[]{}));

        Query query = Query.query(criteria);
        Optional<Social> one = this.findOne(query);
        if (one.isPresent()) {
            throw new BizException(BaseBizStatus.ALREADY_BOUND_USER);
        }
        return this.save(social);
    }

}
