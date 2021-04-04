package com.voc.system.service;

import com.voc.system.entity.Action;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 09:43
 */
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@DisplayName("PageActionTest")
public class IPageActionTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    @DisplayName("add")
    public void add() {
        Action action = new Action();
        action.setName("切换");
        action.setMask(8);
        mongoTemplate.save(action);
    }

    @Test
    @DisplayName("find")
    public void find() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "mask")).limit(1);
        Action one = mongoTemplate.findOne(query, Action.class, "sys_action");
        assertNotNull(one);

    }

}
