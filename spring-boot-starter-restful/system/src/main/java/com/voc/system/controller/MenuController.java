package com.voc.system.controller;

import com.voc.system.entity.Menu;
import com.voc.system.service.IMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/26 15:52
 */
@RestController
@RequestMapping({"${api.system.prefix:}/menu"})
public class MenuController {

    @Resource
    private IMenuService menuService;

    /**
     * 获取所有菜单
     *
     * @return List<Menu>
     */
    @GetMapping
    public List<Menu> get() {
        return menuService.findAll();
    }

    /**
     * 添加菜单
     *
     * @param menu Menu
     * @return 菜单唯一标识
     */
    @PostMapping
    public String add(@RequestBody Menu menu) {
        return menuService.save(menu);
    }

    /**
     * 删除菜单
     *
     * @param id 菜单 ID
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        menuService.deleteById(id);
    }

    @PutMapping
    public Menu update(@RequestBody Menu menu) {
        return menuService.update(menu);
    }


}
