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
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @GetMapping
    public List<Menu> get() {
        return null;
    }

    @PostMapping
    public String add(@RequestBody Menu menu){
        return menuService.save(menu);
    }
}
