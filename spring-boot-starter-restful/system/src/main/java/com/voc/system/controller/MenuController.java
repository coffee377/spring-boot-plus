package com.voc.system.controller;

import com.voc.system.entity.Menu;
import com.voc.system.service.IMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
     * 批量添加菜单
     *
     * @param menus List<Menu>
     * @return 菜单唯一标识集合
     */
    @PostMapping("/batch")
    public List<String> addBatch(@RequestBody List<Menu> menus) {
        return menuService.saveAll(menus);
    }

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        menuService.deleteById(id);
    }

    /**
     * 批量删除菜单
     *
     * @param ids 菜单ID集合
     */
    @DeleteMapping("/batch")
    public void deleteBatch(@RequestBody List<Object> ids) {
        menuService.deleteByIds(ids);
    }

    /**
     * 修改菜单
     *
     * @param menu 待修改菜单数据实体
     * @return 修改后菜单
     */
    @PutMapping
    public Menu update(@RequestBody Menu menu) {
        return menuService.update(menu);
    }

    /**
     * 更新指定ID菜单
     *
     * @param id             主键标识
     * @param updateFieldMap 更新字段信息
     * @return Menu
     */
    @PutMapping("{id}")
    public Menu updateById(@PathVariable String id, @RequestBody Map<String, Object> updateFieldMap) {
        return menuService.updateById(id, updateFieldMap);
    }

    /**
     * 批量修改菜单
     *
     * @param menus 待修改菜单数据实体集合
     * @return 修改后菜单集合
     */
    @PutMapping("/batch")
    public List<Menu> updateBatch(@RequestBody List<Menu> menus) {
        return menuService.updateAll(menus);
    }

    /**
     * 获取所有菜单
     *
     * @return List<Menu>
     */
    @GetMapping
    public List<Menu> findAll() {
        return menuService.findAll();
    }

    /**
     * 获取指定ID菜单
     *
     * @return List<Menu>
     */
    @GetMapping("{id}")
    public Menu findOne(@PathVariable String id) {
        return menuService.findById(id);
    }

}
