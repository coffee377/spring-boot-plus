package com.voc.system.controller;

import com.voc.system.entity.bo.UserBO;
import com.voc.system.entity.vo.UserVO;
import com.voc.system.entity.vo.menu.MenuVO;
import com.voc.system.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/25 12:01
 */
@RestController
@RequestMapping({"/user"})
public class UserController {

    @Resource
    private IUserService userService;


    /**
     * 添加用户
     *
     * @param user UserBO
     */
    @PostMapping
    public void add(@RequestBody UserBO user) {
        userService.save(user);
    }

    /**
     * 批量添加用户
     *
     * @param users List<UserBO>
     */
    @PostMapping("/batch")
    public void addBatch(@RequestBody List<UserBO> users) {
        userService.saveBatch(users);
    }

    /**
     * 删除用户
     *
     * @param uid 用户标识
     */
    @DeleteMapping("/{uid}")
    public void delete(@PathVariable String uid) {
        userService.deleteById(uid);
    }

    /**
     * 批量删除用户
     */
    @DeleteMapping("/batch")
    public void deleteBatch(@RequestBody List<String> ids) {
        userService.deleteByIds(ids);
    }

    /**
     * 更新用户信息
     *
     * @param user UserBO
     */
    @PutMapping("/{uid}")
    public void update(@PathVariable String uid, @RequestBody UserBO user) {
        userService.updateById(uid, user);
    }

    /**
     * 批量更新用户信息
     *
     * @param users List<UserBO>
     */
    @PutMapping("/batch")
    public void updateBatch(@RequestBody List<UserBO> users) {
//        userService.saveBatch(users);
    }

    /**
     * 分页查询用户数据
     *
     * @param pageSize 页面大小
     * @param pageNum  页码
     * @return List<Object>
     */
    @GetMapping
    public List<UserVO> query(@RequestParam int pageSize, @RequestParam int pageNum) {
//        userService.
        return null;
    }

    /**
     * 查询指定用户信息
     *
     * @param uid 用户标识
     * @return UserVO
     */
    @GetMapping("/{uid}")
    public UserVO query(@PathVariable String uid) {
//        userService.
        return null;
    }


    /**
     * 获取当前登录用户信息
     *
     * @return User
     */
    @GetMapping("/info")
    public UserVO userInfo() {
        UserVO userVO = new UserVO();
        return userVO;
    }

    /**
     * 获取当前用户菜单数据
     *
     * @param root 系统根菜单 id
     * @return List<MenuVO>
     */
    @GetMapping("/menus")
    public List<MenuVO> userMenus(@RequestParam String root) {
        return null;
    }
}
