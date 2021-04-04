package com.voc.system.dao;

import com.voc.system.entity.Action;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 16:05
 */
@SpringBootTest
class IActionDaoTest {

    @Resource
    private IActionDao actionDao;

    @Test
    void getLatestMask() {
//        int latestMask = actionRepository.getLatestMask();
//        assertTrue(latestMask > 0);
    }

    @Test
    void findAll(){
        List<Action> all = actionDao.findAll();
//        assertNotNull(all);
        assertNull(all);
    }

    @Test
    void add() {
        actionDao.add("查询", 0);
        actionDao.add("添加", 1);
        actionDao.add("删除", 2);
        actionDao.add("保存", 3);
        actionDao.add("导入", 4);
        actionDao.add("导出", 5);
        actionDao.add("切换", 6);
        actionDao.add("启用", 7);
        actionDao.add("锁定", 8);
//        actionDao.add()
    }

    @Test
    void update(){
        Action action = new Action();
        action.setId("601d306a7b723109220fc87e");
        action.setName("锁定33");
        action.setMask(9);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.
        actionDao.updateById("601d306a7b723109220fc87e",new HashMap<String,Object>(){{
            put("name", "锁定");
            put("mask", 8);
        }});
    }

    @Test
    void delete() {
        actionDao.deleteById("601d306a7b723109220fc87e");
//        actionRepository.deleteByMasks(6, 7, 8);
    }
}
