package com.voc.system.controller;

import com.voc.system.entity.Menu;
import com.voc.system.entity.User;
import com.voc.system.service.IMenuService;
import com.voc.system.service.IUserService;
import com.voc.system.vo.user.UserVO;
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

    @Resource
    private IMenuService menuService;

    @PostMapping
    public String add(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * 获取当前登录用户信息（菜单、角色、权限等）
     *
     * @return User
     */
    @GetMapping("/info")
//    @PreAuthorize("isAuthenticated()")
    public UserVO userInfo() {
//        String name = authentication.getName();
//        User user = userService.getUserByUsername(name);
        UserVO userVO = new UserVO();
//        userVO.setUsername(user.getUsername());
//        userVO.setRealName(user.getRealName());
//        userVO.setAvatar(user.getAvatar());
        List<Menu> menus = menuService.findAll();
//        userVO.setMenus(menus);
        return userVO;
    }

//    @DeleteMapping("{uid}")
//    public Result delete(@PathVariable String uid) {
//        userService.deleteById(uid);
//        return Result.success();
//    }

}
