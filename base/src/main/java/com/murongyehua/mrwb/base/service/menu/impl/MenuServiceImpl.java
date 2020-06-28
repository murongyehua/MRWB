package com.murongyehua.mrwb.base.service.menu.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.murongyehua.mrwb.api.param.MenuParam;
import com.murongyehua.mrwb.api.req.MenuGetReq;
import com.murongyehua.mrwb.api.resp.MenuListResp;
import com.murongyehua.mrwb.api.resp.ModalListResp;
import com.murongyehua.mrwb.base.dao.mapper.BaseMenuMapper;
import com.murongyehua.mrwb.base.dao.po.BaseMenuPO;
import com.murongyehua.mrwb.base.dao.po.BaseModalInfoPO;
import com.murongyehua.mrwb.base.service.menu.MenuService;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENCommonState;
import com.murongyehua.mrwb.commom.enums.ENUserType;
import com.murongyehua.mrwb.commom.user.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liul
 * @version 1.0 2020/6/22
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private BaseMenuMapper menuMapper;

    private final static String MANAGER_MENU_PARAM = "systemManage";

    @Override
    public ResultContext getMenu(MenuGetReq req) {
        String userType = UserContext.getUserInfo().getUserType();
        if (ENUserType.MANAGER.getValue().equals(userType)) {
            BaseMenuPO param = new BaseMenuPO();
            param.setRouteIndex(MANAGER_MENU_PARAM);
            ResultContext resultContext = ResultContext.success("操作成功");
            resultContext.setData(menuMapper.selectBySelective(param));
            return resultContext;
        }
        return ResultContext.businessFail("暂不支持的用户类型");
    }

    @Override
    public PageView queryList(MenuParam param) {
        BaseMenuPO menuPO = new BaseMenuPO();
        BeanUtil.copyProperties(param, menuPO);
        PageHelper.startPage(param.getPageNum(), param.getPageSize(), param.getOrderBy());
        PageInfo pageInfo = new PageInfo(this.translate(menuMapper.selectBySelective(menuPO)));
        PageView pageView = new PageView();
        BeanUtil.copyProperties(ResultContext.success("操作成功"), pageView);
        pageView.setRows(pageInfo.getList());
        pageView.setTotal(pageInfo.getTotal());
        return pageView;
    }

    private List<MenuListResp> translate(List<BaseMenuPO> list) {
        List<MenuListResp> result = new ArrayList<>();
        int num = 1;
        for (BaseMenuPO menuPO : list) {
            MenuListResp menuListResp = new MenuListResp();
            BeanUtil.copyProperties(menuPO, menuListResp);
            menuListResp.setIndex(num);
            result.add(menuListResp);
            num++;
        }
        return result;
    }

}
