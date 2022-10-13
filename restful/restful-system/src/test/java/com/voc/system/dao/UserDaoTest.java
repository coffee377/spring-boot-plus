package com.voc.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.voc.system.entity.po.UserPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/15 09:10
 */
@Slf4j
@DisplayName("用户持久化测试")
@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {

    @Resource
    private UserDao userDao;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("添加用户")
    public void add() {
        UserPO userPO = new UserPO();
//        userPO.setUsername("coffee377");
//        userPO.setPassword("123456");
        userPO.setRealName("吴玉杰");
        userDao.insert(userPO);
    }

    @Test
    public void query(){
        UserPO userPO = userDao.selectOne(new QueryWrapper<UserPO>().eq("USERNAME", "coffee377"));
    }

    @Test
    public void update(){
        UserPO userPO = new UserPO();
        userPO.setEmail("coffee377@dingtalk.com");
        userPO.setJobNumber("0634");
        userPO.setMobile("13956945898");
        userDao.update(userPO, new QueryWrapper<UserPO>().eq("USERNAME", "coffee377"));
    }

    @Test
    public void delete(){
        userDao.delete(new QueryWrapper<UserPO>().eq("USERNAME", "coffee377"));
    }
}
