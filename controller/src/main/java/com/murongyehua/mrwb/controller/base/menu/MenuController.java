package com.murongyehua.mrwb.controller.base.menu;

import com.murongyehua.mrwb.api.param.MenuParam;
import com.murongyehua.mrwb.api.req.MenuGetReq;
import com.murongyehua.mrwb.base.service.menu.MenuService;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liul
 * @version 1.0 2020/6/22
 */
@RestController
@RequestMapping("/base/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/getMenu.do")
    public ResultContext getMenu(MenuGetReq req){
        return menuService.getMenu(req);
    }

    @PostMapping("/query.smg")
    public PageView query(MenuParam param) {
        return menuService.queryList(param);
    }
}
